package com.globe.restservices.service;

import com.globe.restservices.model.*;
import com.globe.restservices.repository.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class ImportService {
    private static String sheetName = "Mapping";
    private static List<String> invalidSheet = Arrays.asList("Version", "SampleData", "Business Rules");
    private static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    AttributeSpecificRepository attributeSpecificRepository;

    @Autowired
    AuditRepository auditRepository;

    @Autowired
    DatabaseRepository databaseRepository;

    @Autowired
    EntityRepository entityRepository;

    @Autowired
    EntityTransformationRepository entityTransformationRepository;

    @Autowired
    MultipleSourceRepository multipleSourceRepository;

    @Async
    public void excelToMapping(String filename, byte[] bs) {
        try {
            InputStream is = new ByteArrayInputStream(bs);

            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();

            List<Mapping> mappingList = new ArrayList<Mapping>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Mapping mapping = new Mapping();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    String cellValue = getCellStringValue(currentCell);

                    switch (cellIdx) {
                        case 0:
                            mapping.setSourceLocation(cellValue);
                            break;

                        case 1:
                            mapping.setSourceEntity(cellValue);
                            break;

                        case 2:
                            mapping.setSourceAttribute(cellValue);
                            break;

                        case 4:
                            mapping.setSourceComments(cellValue);
                            break;

                        case 5:
                            mapping.setMultipleSource(cellValue);
                            break;

                        case 7:
                            mapping.setTransformationRules(cellValue);
                            break;

                        case 8:
                            mapping.setSubjectArea(cellValue);
                            break;

                        case 9:
                            mapping.setTargetEntity(cellValue);
                            break;

                        case 10:
                            mapping.setTargetAttribute(cellValue);
                            break;

                        case 11:
                            if (cellValue.equals("")) {
                                mapping.setPosition(null);
                            } else {
                                mapping.setPosition(cellValue);
                            }
                            break;

                        case 12:
                            mapping.setDescription(cellValue);
                            break;

                        case 13:
                            mapping.setPrimaryKey(cellValue);
                            break;

                        case 14:
                            mapping.setPrimaryIndex(cellValue);
                            break;

                        case 15:
                            mapping.setNotOption(cellValue);
                            break;

                        case 16:
                            mapping.setDataType(cellValue);
                            break;

                        case 17:
                            if (cellValue == "") {
                                if (currentCell.getCellType() != CellType.STRING) {
                                    mapping.setMapped((int) currentCell.getNumericCellValue());
                                }
                            } else {
                                mapping.setMapped(Integer.parseInt(cellValue));
                            }
                            break;

                        case 18:
                            mapping.setUpdatedBy(cellValue);
                            break;

                        case 19:
                            mapping.setUpdatedVersion(cellValue);
                            break;

                        case 20:
                            mapping.setUpdateDate(cellValue);
                            break;

                        case 21:
                            mapping.setComments(cellValue);
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                mappingList.add(mapping);
            }

            workbook.close();

            //return mappingList;
            insertToDatabase(filename, mappingList);
            System.out.println(Thread.currentThread().getName());
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    @Transactional
    public void insertToDatabase(String filename, List<Mapping> mappingSpecificationList) {
        //Check if source database, table and column exist. If yes get ids if no insert then get ids
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //Get target database based on filename
        String targetDatabaseName = "";
        //Could be change to a file to easily add target database
        if(filename.equals("extract")) {
            targetDatabaseName = "zz2_broadband_datamart";
        } else if (filename.split("-")[0].equals("RDB")) {
            targetDatabaseName = "zz2_rdb";
        } else if(filename.split("-")[0].equals("NDM")) {
            targetDatabaseName = "zz2_network_datamart";
        } else if(filename.split("-")[0].equals("BBMart")) {
            targetDatabaseName = "zz2_broadband_datamart";
        }  else {
            //Throw error
            //get first string after the delimiter
        }
        System.out.println("target database name: " + targetDatabaseName);

        Database targetDatabase = databaseRepository.findByDatabaseName(targetDatabaseName);

        if (targetDatabase == null) {
            targetDatabase = databaseRepository.save(new Database(
                    targetDatabaseName,
                    true
            ));

            if (!targetDatabase.getDatabaseId().toString().isEmpty()) {
                auditRepository.save(new Audit("Database", targetDatabase.getDatabaseId(), "Database Name", targetDatabase.getDatabaseName(), timestamp));
                auditRepository.save(new Audit("Database", targetDatabase.getDatabaseId(), "Active Indicator", String.valueOf(targetDatabase.isActiveIndicator()), timestamp));
            }
        }

        String prevMultipleSource = null;
        Integer prevMultipleSourceId = null;
        String prevtargetEntity = null;
        Integer prevtargetEntityId = null;

        for (Mapping mappingSpecification : mappingSpecificationList) {
            System.out.println(mappingSpecification);
            Database sourceDatabase = null;
            Entity sourceEntity = null;
            Attribute sourceAttribute = new Attribute();
            Entity targetEntity = new Entity();
            MultipleSource multipleSource = new MultipleSource();
            Attribute targetAttribute = null;
            AttributeSpecific targetAttributeSpecific = null;
            boolean skipSource = false;
            boolean skipTargetEntity = false;
            boolean skipMultipleSource = false;

            if (mappingSpecification.getMapped().equals(0) || mappingSpecification.getMapped().equals(3) || mappingSpecification.getMapped().equals(4) || mappingSpecification.getMapped().equals(5) || mappingSpecification.getMapped().equals(6) || mappingSpecification.getTransformationRules().equals("ETL Generated Value")) {
                skipSource = true;
                sourceAttribute.setAttributeId(null);
                System.out.println("Skipping source");
            }

            if (mappingSpecification.getTargetEntity().equals(prevtargetEntity)) {
                skipTargetEntity = true;
                targetEntity.setEntityId(prevtargetEntityId);
                System.out.println("Skipping target entity " + mappingSpecification.getTargetEntity() + " : " + prevtargetEntity);
            }

            if (mappingSpecification.getMultipleSource().equals(prevMultipleSource) && mappingSpecification.getTargetEntity().equals(prevtargetEntity)) {
                skipMultipleSource = true;
                multipleSource.setMultipleSourceId(prevMultipleSourceId);
                System.out.println("Skipping multiple source " + mappingSpecification.getMultipleSource() + " : " + prevMultipleSource + " " + prevMultipleSourceId);
            }

            if (!skipSource) {
                sourceDatabase = databaseRepository.findByDatabaseName(mappingSpecification.getSourceLocation());

                if (sourceDatabase == null) {
                    sourceDatabase = databaseRepository.save(new Database(
                            mappingSpecification.getSourceLocation(),
                            true
                    ));

                    if (!sourceDatabase.getDatabaseId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Database", sourceDatabase.getDatabaseId(), "Database Name", sourceDatabase.getDatabaseName(), timestamp));
                        auditRepository.save(new Audit("Database", sourceDatabase.getDatabaseId(), "Active Indicator", String.valueOf(sourceDatabase.isActiveIndicator()), timestamp));
                    }
                }

                sourceEntity = entityRepository.findByEntityNameAndDatabaseId(mappingSpecification.getSourceEntity(), sourceDatabase.getDatabaseId());

                if (sourceEntity == null) {
                    sourceEntity = entityRepository.save(new Entity(
                            sourceDatabase.getDatabaseId(),
                            mappingSpecification.getSourceEntity(),
                            true
                    ));

                    if (!sourceEntity.getEntityId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Entity", sourceEntity.getEntityId(), "Database Id", String.valueOf(sourceEntity.getDatabaseId()), timestamp));
                        auditRepository.save(new Audit("Entity", sourceEntity.getEntityId(), "Entity Name", sourceEntity.getEntityName(), timestamp));
                        auditRepository.save(new Audit("Entity", sourceEntity.getEntityId(), "Active Indicator", String.valueOf(sourceEntity.isActiveIndicator()), timestamp));
                    }
                }

                sourceAttribute = attributeRepository.findByAttributeNameAndEntityId(mappingSpecification.getSourceAttribute(), sourceEntity.getEntityId());

                if (sourceAttribute == null) {
                    sourceAttribute = attributeRepository.save(new Attribute(
                            sourceEntity.getEntityId(),
                            mappingSpecification.getSourceAttribute(),
                            null,
                            null,
                            false,
                            false,
                            false,
                            null,
                            true
                    ));

                    if (!sourceAttribute.getAttributeId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Entity Id", sourceAttribute.getEntityId().toString(), timestamp));
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Attribute Name", sourceAttribute.getAttributeName(), timestamp));
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Data Type Value", sourceAttribute.getDataTypeValue(), timestamp));
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Attribute Description", sourceAttribute.getAttributeDescription(), timestamp));
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Nullable Indicator", String.valueOf(sourceAttribute.isNullableIndicator()), timestamp));
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Primary Key Indicator", String.valueOf(sourceAttribute.isPrimaryKeyIndicator()), timestamp));
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Primary Index Indicator", String.valueOf(sourceAttribute.isPrimaryIndexIndicator()), timestamp));
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Position Count", null, timestamp));
                        auditRepository.save(new Audit("Attribute", sourceAttribute.getAttributeId(), "Active Indicator", String.valueOf(sourceAttribute.isActiveIndicator()), timestamp));
                    }
                }
            }
            if (!skipTargetEntity) {
                //Target Check if exist
                targetEntity = entityRepository.findByEntityNameAndDatabaseId(mappingSpecification.getSourceEntity(), targetDatabase.getDatabaseId());

                if (targetEntity == null) {
                    targetEntity = entityRepository.save(new Entity(
                            targetDatabase.getDatabaseId(),
                            mappingSpecification.getTargetEntity(),
                            true
                    ));

                    if (!targetEntity.getEntityId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Entity", targetEntity.getEntityId(), "Database Id", String.valueOf(targetEntity.getDatabaseId()), timestamp));
                        auditRepository.save(new Audit("Entity", targetEntity.getEntityId(), "Entity Name", targetEntity.getEntityName(), timestamp));
                        auditRepository.save(new Audit("Entity", targetEntity.getEntityId(), "Active Indicator", String.valueOf(targetEntity.isActiveIndicator()), timestamp));
                    }
                }

                multipleSource = multipleSourceRepository.findBySourceNameAndEntityId(mappingSpecification.getMultipleSource(), targetEntity.getEntityId());

                if (multipleSource == null) {
                    multipleSource = multipleSourceRepository.save(new MultipleSource(
                            targetEntity.getEntityId(),
                            mappingSpecification.getMultipleSource(),
                            true
                    ));

                    if (!multipleSource.getMultipleSourceId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Multiple Source", multipleSource.getMultipleSourceId(), "Entity Id", multipleSource.getEntityId().toString(), timestamp));
                        auditRepository.save(new Audit("Multiple Source", multipleSource.getMultipleSourceId(), "Source Name", multipleSource.getSourceName(), timestamp));
                        auditRepository.save(new Audit("Multiple Source", multipleSource.getMultipleSourceId(), "Active Indicator", String.valueOf(multipleSource.isActiveIndicator()), timestamp));
                    }
                }

                if (mappingSpecification.getMapped().equals(4)) {
                    EntityTransformation entityTransformation = entityTransformationRepository.save(new EntityTransformation(
                            multipleSource.getMultipleSourceId(),
                            mappingSpecification.getTransformationRules()
                    ));

                    if (!entityTransformation.getEntityTransformationId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Entity Transformation", entityTransformation.getEntityTransformationId(), "Multiple Source Id", entityTransformation.getMultipleSourceId().toString(), timestamp));
                        auditRepository.save(new Audit("Entity Transformation", entityTransformation.getEntityTransformationId(), "Transformation Value", entityTransformation.getTransformationValue(), timestamp));
                    }

                    //Insert entity specific
                }
            } else if (!skipMultipleSource) {

                multipleSource = multipleSourceRepository.findBySourceNameAndEntityId(mappingSpecification.getMultipleSource(), targetEntity.getEntityId());

                if (multipleSource == null) {
                    multipleSource = multipleSourceRepository.save(new MultipleSource(
                            targetEntity.getEntityId(),
                            mappingSpecification.getMultipleSource(),
                            true
                    ));

                    if (!multipleSource.getMultipleSourceId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Multiple Source", multipleSource.getMultipleSourceId(), "Entity Id", multipleSource.getEntityId().toString(), timestamp));
                        auditRepository.save(new Audit("Multiple Source", multipleSource.getMultipleSourceId(), "Source Name", multipleSource.getSourceName(), timestamp));
                        auditRepository.save(new Audit("Multiple Source", multipleSource.getMultipleSourceId(), "Active Indicator", String.valueOf(multipleSource.isActiveIndicator()), timestamp));
                    }
                }

                if (mappingSpecification.getMapped().equals(4)) {
                    EntityTransformation entityTransformation = entityTransformationRepository.save(new EntityTransformation(
                            multipleSource.getMultipleSourceId(),
                            mappingSpecification.getTransformationRules()
                    ));

                    if (!entityTransformation.getEntityTransformationId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Entity Transformation", entityTransformation.getEntityTransformationId(), "Multiple Source Id", entityTransformation.getMultipleSourceId().toString(), timestamp));
                        auditRepository.save(new Audit("Entity Transformation", entityTransformation.getEntityTransformationId(), "Transformation Value", entityTransformation.getTransformationValue(), timestamp));
                    }

                    //Insert entity specific
                }
            } else {
                targetAttribute = attributeRepository.findByAttributeNameAndEntityId(mappingSpecification.getTargetAttribute(), targetEntity.getEntityId());

                if (targetAttribute == null) {
                    if (mappingSpecification.getPosition() == null) {
                        targetAttribute = attributeRepository.save(new Attribute(
                                targetEntity.getEntityId(),
                                mappingSpecification.getTargetAttribute(),
                                mappingSpecification.getDataType(),
                                mappingSpecification.getDescription(),
                                Boolean.parseBoolean(mappingSpecification.getNotOption()),
                                Boolean.parseBoolean(mappingSpecification.getPrimaryKey()),
                                Boolean.parseBoolean(mappingSpecification.getPrimaryIndex()),
                                null,
                                true
                        ));
                    } else {
                        targetAttribute = attributeRepository.save(new Attribute(
                                targetEntity.getEntityId(),
                                mappingSpecification.getTargetAttribute(),
                                mappingSpecification.getDataType(),
                                mappingSpecification.getDescription(),
                                Boolean.parseBoolean(mappingSpecification.getNotOption()),
                                Boolean.parseBoolean(mappingSpecification.getPrimaryKey()),
                                Boolean.parseBoolean(mappingSpecification.getPrimaryIndex()),
                                Integer.parseInt(mappingSpecification.getPosition()),
                                true
                        ));
                    }

                    if (!targetAttribute.getAttributeId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Entity Id", targetAttribute.getEntityId().toString(), timestamp));
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Attribute Name", targetAttribute.getAttributeName(), timestamp));
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Data Type Value", targetAttribute.getDataTypeValue(), timestamp));
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Attribute Description", targetAttribute.getAttributeDescription(), timestamp));
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Nullable Indicator", String.valueOf(targetAttribute.isNullableIndicator()), timestamp));
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Primary Key Indicator", String.valueOf(targetAttribute.isPrimaryKeyIndicator()), timestamp));
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Primary Index Indicator", String.valueOf(targetAttribute.isPrimaryIndexIndicator()), timestamp));
                        if (targetAttribute.getPositionCount() == null) {
                            auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Position Count", null, timestamp));
                        } else {
                            auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Position Count", targetAttribute.getPositionCount().toString(), timestamp));
                        }
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Active Indicator", String.valueOf(targetAttribute.isActiveIndicator()), timestamp));
                    }
                } else {
                    //Update attribute if it is not equal to existing
                    String _dataTypeValue = targetAttribute.getDataTypeValue();
                    String _attributeDescription = targetAttribute.getAttributeDescription();
                    boolean _nullableIndicator = targetAttribute.isNullableIndicator();
                    boolean _primaryKeyIndicator = targetAttribute.isPrimaryKeyIndicator();
                    boolean _primaryIndexIndicator = targetAttribute.isPrimaryIndexIndicator();
                    Integer _positionCount = targetAttribute.getPositionCount();

                    targetAttribute.setDataTypeValue(mappingSpecification.getDataType());
                    targetAttribute.setAttributeDescription(mappingSpecification.getDescription());
                    targetAttribute.setNullableIndicator(Boolean.parseBoolean(mappingSpecification.getNotOption()));
                    targetAttribute.setPrimaryKeyIndicator(Boolean.parseBoolean(mappingSpecification.getPrimaryKey()));
                    targetAttribute.setPrimaryIndexIndicator(Boolean.parseBoolean(mappingSpecification.getPrimaryIndex()));
                    if (mappingSpecification.getPosition() == null) {
                        targetAttribute.setPositionCount(null);
                    } else {
                        targetAttribute.setPositionCount(Integer.parseInt(mappingSpecification.getPosition()));
                    }

                    attributeRepository.save(targetAttribute);

                    if (_dataTypeValue != mappingSpecification.getDataType()) {
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Data Type Value", targetAttribute.getDataTypeValue(), timestamp));
                    }
                    if (_attributeDescription != mappingSpecification.getDescription()) {
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Attribute Description", targetAttribute.getAttributeDescription(), timestamp));
                    }
                    if (_nullableIndicator != Boolean.parseBoolean(mappingSpecification.getNotOption())) {
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Nullable Indicator", String.valueOf(targetAttribute.isNullableIndicator()), timestamp));
                    }
                    if (_primaryKeyIndicator != Boolean.parseBoolean(mappingSpecification.getPrimaryKey())) {
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Primary Key Indicator", String.valueOf(targetAttribute.isPrimaryKeyIndicator()), timestamp));
                    }
                    if (_primaryIndexIndicator != Boolean.parseBoolean(mappingSpecification.getPrimaryIndex())) {
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Primary Index Indicator", String.valueOf(targetAttribute.isPrimaryIndexIndicator()), timestamp));
                    }
                    if (mappingSpecification.getPosition() != null) {
                        if (_positionCount != Integer.parseInt(mappingSpecification.getPosition())) {
                            auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Position Count", targetAttribute.getPositionCount().toString(), timestamp));
                        }
                    } else {
                        auditRepository.save(new Audit("Attribute", targetAttribute.getAttributeId(), "Position Count", null, timestamp));
                    }
                }
                targetAttributeSpecific = attributeSpecificRepository.findByAttributeIdAndMultipleSourceId(targetAttribute.getAttributeId(), multipleSource.getMultipleSourceId());

                if (targetAttributeSpecific == null) {
                    targetAttributeSpecific = attributeSpecificRepository.save(new AttributeSpecific(
                            targetAttribute.getAttributeId(),
                            multipleSource.getMultipleSourceId(),
                            sourceAttribute.getAttributeId(),
                            mappingSpecification.getTransformationRules(),
                            mappingSpecification.getComments(),
                            true
                    ));

                    if (!targetAttributeSpecific.getAttributeSpecificId().toString().isEmpty()) {
                        auditRepository.save(new Audit("Attribute Specific", targetAttributeSpecific.getAttributeSpecificId(), "Attribute Id", targetAttributeSpecific.getAttributeId().toString(), timestamp));
                        auditRepository.save(new Audit("Attribute Specific", targetAttributeSpecific.getAttributeSpecificId(), "Multiple Source Id", targetAttributeSpecific.getMultipleSourceId().toString(), timestamp));
                        if (sourceAttribute.getAttributeId() == null) {
                            auditRepository.save(new Audit("Attribute Specific", targetAttributeSpecific.getAttributeSpecificId(), "Source Attribute Id", null, timestamp));
                        } else {
                            auditRepository.save(new Audit("Attribute Specific", targetAttributeSpecific.getAttributeSpecificId(), "Source Attribute Id", targetAttributeSpecific.getSourceAttributeId().toString(), timestamp));
                        }
                        auditRepository.save(new Audit("Attribute Specific", targetAttributeSpecific.getAttributeSpecificId(), "Transformation Value", targetAttributeSpecific.getTransformationValue(), timestamp));
                        auditRepository.save(new Audit("Attribute Specific", targetAttributeSpecific.getAttributeSpecificId(), "Attribute Comment Value", targetAttributeSpecific.getAttributeCommentValue(), timestamp));
                        auditRepository.save(new Audit("Attribute Specific", targetAttributeSpecific.getAttributeSpecificId(), "Active Indicator", String.valueOf(targetAttributeSpecific.isActiveIndicator()), timestamp));
                    }
                }
            }
            prevMultipleSource = mappingSpecification.getMultipleSource();
            prevtargetEntity = mappingSpecification.getTargetEntity();
            prevMultipleSourceId = multipleSource.getMultipleSourceId();
            prevtargetEntityId = targetEntity.getEntityId();
        }
    }

    @Async
    public void excelToExtract(byte[] bs) {
        try {
            InputStream is = new ByteArrayInputStream(bs);

            Workbook workbook = new XSSFWorkbook(is);

            List<String> sheetNames = new ArrayList<String>();
            for (int i=0; i<workbook.getNumberOfSheets(); i++) {
                sheetNames.add( workbook.getSheetName(i) );
            }

            List<ExtractField> fieldList = new ArrayList<ExtractField>();
            List<MainFilter> mainFilterList = new ArrayList<MainFilter>();
            List<ExtractEntity> extractEntityList = new ArrayList<ExtractEntity>();

            for (String sheetName :  sheetNames) {
                if (invalidSheet.indexOf(sheetName) > -1) {
                    //do nothing
                } else {
                    System.out.println(sheetName);
                    if (sheetName.contains("Field")) {
                        Sheet sheet = workbook.getSheet(sheetName);
                        Iterator<Row> rows = sheet.iterator();

                        int rowNumber = 0;
                        while (rows.hasNext()) {
                            Row currentRow = rows.next();

                            // skip header
                            if (rowNumber == 0) {
                                rowNumber++;
                                continue;
                            }

                            Iterator<Cell> cellsInRow = currentRow.iterator();

                            ExtractField field = new ExtractField();

                            int cellIdx = 0;
                            while (cellsInRow.hasNext()) {
                                Cell currentCell = cellsInRow.next();

                                String cellValue = getCellStringValue(currentCell);

                                switch (cellIdx) {
                                    case 0:
                                        field.setReportField(cellValue);
                                        break;

                                    case 1:
                                        field.setAvailability(cellValue);
                                        break;

                                    case 2:
                                        field.setDatabase(cellValue);
                                        break;

                                    case 3:
                                        field.setEntity(cellValue);
                                        break;

                                    case 4:
                                        field.setAttribute(cellValue);
                                        break;

                                    case 5:
                                        field.setLogic(cellValue);
                                        break;

                                    case 6:
                                        field.setLastUpdatedBy(cellValue);
                                        break;

                                    case 7:
                                        field.setLastUpdatedDate(cellValue);
                                        break;

                                    case 8:
                                        field.setRemark(cellValue);
                                        break;

                                    default:
                                        break;
                                }

                                cellIdx++;
                            }
                            fieldList.add(field);
                        }
                    } else if (sheetName.contains("Main Filter")) {
                        Sheet sheet = workbook.getSheet(sheetName);
                        Iterator<Row> rows = sheet.iterator();

                        int rowNumber = 0;
                        while (rows.hasNext()) {
                            Row currentRow = rows.next();

                            // skip header
                            if (rowNumber == 0) {
                                rowNumber++;
                                continue;
                            }

                            Iterator<Cell> cellsInRow = currentRow.iterator();

                            MainFilter mainFilter = new MainFilter();

                            int cellIdx = 0;
                            while (cellsInRow.hasNext()) {
                                Cell currentCell = cellsInRow.next();

                                String cellValue = getCellStringValue(currentCell);

                                switch (cellIdx) {
                                    case 0:
                                        mainFilter.setExtractName(cellValue);
                                        break;

                                    case 1:
                                        mainFilter.setGranularity(cellValue);
                                        break;

                                    case 2:
                                        mainFilter.setFilterDescription(cellValue);
                                        break;

                                    case 3:
                                        mainFilter.setFilter(cellValue);
                                        break;

                                    case 4:
                                        mainFilter.setRemark(cellValue);
                                        break;

                                    case 5:
                                        mainFilter.setLastUpdatedBy(cellValue);
                                        break;

                                    case 6:
                                        mainFilter.setLastUpdatedDate(cellValue);
                                        break;

                                    default:
                                        break;
                                }

                                cellIdx++;
                            }
                            mainFilterList.add(mainFilter);
                        }
                    } else if (sheetName.contains("Entities")) {
                        Sheet sheet = workbook.getSheet(sheetName);
                        Iterator<Row> rows = sheet.iterator();

                        int rowNumber = 0;
                        while (rows.hasNext()) {
                            Row currentRow = rows.next();

                            // skip header
                            if (rowNumber == 0) {
                                rowNumber++;
                                continue;
                            }

                            Iterator<Cell> cellsInRow = currentRow.iterator();

                            ExtractEntity extractEntity = new ExtractEntity();

                            int cellIdx = 0;
                            while (cellsInRow.hasNext()) {
                                Cell currentCell = cellsInRow.next();

                                String cellValue = getCellStringValue(currentCell);

                                switch (cellIdx) {
                                    case 0:
                                        extractEntity.setDrivingTable(cellValue);
                                        break;

                                    case 1:
                                        extractEntity.setRelationalTable(cellValue);
                                        break;

                                    case 2:
                                        extractEntity.setJoinType(cellValue);
                                        break;

                                    case 3:
                                        extractEntity.setJoinCondition(cellValue);
                                        break;

                                    case 4:
                                        extractEntity.setRemark(cellValue);
                                        break;

                                    case 5:
                                        extractEntity.setLastUpdatedBy(cellValue);
                                        break;

                                    case 6:
                                        extractEntity.setLastUpdatedDate(cellValue);
                                        break;

                                    default:
                                        break;
                                }

                                cellIdx++;
                            }
                            extractEntityList.add(extractEntity);
                        }
                    } else {
                        //log
                    }
                }
            }
            extractToMapping(fieldList, mainFilterList, extractEntityList);
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public void extractToMapping(List<ExtractField> fieldList, List<MainFilter> mainFilterList, List<ExtractEntity> extractEntityList) {
        List<Mapping> mappingList = new ArrayList<Mapping>();

        String prevMultipleSource = null;
        Integer i = 1;
        List<String> mainTableList = new ArrayList<String>();
        for (ExtractField field : fieldList) {
            if(field.getReportField() != null) {
                if (!(field.getReportField().equals("") && field.getAvailability().equals("") && field.getDatabase().equals("") && field.getEntity().equals("") && field.getAttribute().equals("")
                        && field.getLogic().equals("") && field.getLastUpdatedBy().equals("") && field.getLastUpdatedDate().equals("") && field.getRemark().equals(""))) {
                    Mapping mapping = new Mapping();
                    if (!field.getAvailability().equals("")) {
                        mapping.setSourceLocation(field.getDatabase());
                        mapping.setSourceEntity(field.getEntity());
                        mapping.setSourceAttribute(field.getAttribute());
                        mapping.setMultipleSource(prevMultipleSource);
                        mapping.setTransformationRules(field.getLogic());
                        mapping.setTargetAttribute(field.getReportField());
                        mapping.setPosition(i.toString());
                        mapping.setPrimaryKey("False");
                        mapping.setPrimaryIndex("False");
                        mapping.setNotOption("False");
                        mapping.setDataType("");
                        mapping.setUpdatedBy(field.getLastUpdatedBy());
                        mapping.setUpdateDate(field.getLastUpdatedDate());
                        mapping.setUpdatedBy(field.getRemark());
                        if (field.getAttribute() == "Derived") {
                            mapping.setMapped(3);
                        } else {
                            mapping.setMapped(1);
                        }
                        i++;
                    } else {
                        if (prevMultipleSource != field.getReportField()) {
                            prevMultipleSource = field.getReportField();
                            mapping.setMultipleSource(prevMultipleSource);
                            mapping.setMapped(4);
                            i = 1;
                            mainTableList.add(prevMultipleSource);
                        }
                    }

                    mappingList.add(mapping);
                    //System.out.println(field);
                    //System.out.println(mapping);
                }
            }
        }


        String mainTable = null;
        String transformation = null;
        String lastUpdatedBy = null;
        String lastUpdatedDate = null;
        String remark = null;
        List<ExtractTransformation> extractTransformationList = new ArrayList<ExtractTransformation>();
        for (ExtractEntity extractEntity : extractEntityList) {
            if(extractEntity.getDrivingTable() != null && extractEntity.getRelationalTable() != null) {
                if(mainTableList.indexOf(extractEntity.getDrivingTable())  > -1) {
                    transformation = transformation + " " + extractEntity.getJoinType() + " " + extractEntity.getRelationalTable() + " ON " + extractEntity.getJoinCondition();
                } else {
                    if(mainTable != extractEntity.getDrivingTable()) {
                        if (mainTable != null && transformation != null) {
                            ExtractTransformation extractTransformation = new ExtractTransformation();
                            extractTransformation.setMainTable(mainTable);
                            extractTransformation.setTransformation(transformation);
                            extractTransformation.setLastUpdatedBy(lastUpdatedBy);
                            extractTransformation.setLastUpdatedDate(lastUpdatedDate);
                            extractTransformation.setRemarks(remark);

                            System.out.println(mainTable);
                            extractTransformationList.add(extractTransformation);
                        }
                        mainTable = extractEntity.getDrivingTable();
                        transformation = "FROM " + extractEntity.getDrivingTable() + " " + extractEntity.getJoinType()
                                + " " + extractEntity.getRelationalTable() + " ON " + extractEntity.getJoinCondition();
                        lastUpdatedBy = extractEntity.getLastUpdatedBy();
                        lastUpdatedDate = extractEntity.getLastUpdatedDate();
                        remark = extractEntity.getRemark();
                    }
                }
            }
        }
        //System.exit(1);

        String extractName = null;
        String filter = "Filter: ";
        for(MainFilter mf : mainFilterList) {
            if(mf.getExtractName() != null) {
                extractName = mf.getExtractName();
            }
            if(mf.getFilter() != null) {
                filter = filter + mf.getFilter();
            }
        }

        for (Mapping mapping : mappingList) {
            if(mapping.getMapped().equals(4)) {
                for (ExtractTransformation et : extractTransformationList) {
                    if (et.getMainTable().contains(mapping.getMultipleSource().toLowerCase())) {
                        mapping.setTransformationRules(et.getTransformation() + "  " + filter);
                        mapping.setTargetEntity(extractName);
                        mapping.setUpdateDate(et.getLastUpdatedDate());
                        mapping.setUpdatedBy(et.getLastUpdatedBy());
                        mapping.setComments(et.getRemarks());
                    }
                }
            } else {
                mapping.setTargetEntity(extractName);
            }
            //System.out.println(mapping);
        }
        insertToDatabase("extract", mappingList);
    }

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static String getCellStringValue(Cell cell) {
        if (null != cell) {
            switch (cell.getCellType()) {
                case BLANK:
                case ERROR:
                case FORMULA:
                    return "";
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case NUMERIC:
                    Double d = cell.getNumericCellValue();
                    Long l = d.longValue();
                    if (l.doubleValue() == d) {
                        return l.toString();
                    } else {
                        return d.toString();
                    }
                default:
                    return cell.getStringCellValue();
            }
        }
        return "";
    }
}
