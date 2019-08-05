package com.gaic.services.mdmreferencedata.dao;

/* 
to view data in HSQLDB DatabaseManagerSwing

in eclipse
1) select Window->Show View ->Display
2) in the display window copy the below data
	org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url",  "jdbc:hsqldb:mem:.", "--noexit"});
3) select the string you just copied into the Display window.
4) right click and select "execute"	 or click the execute icon 
*/
public class MdmReferenceDataSql
{
	
	public final static String SUBJECT_ID_COLUMN_NAME = "SUBJECT_ID";
	public final static String GAI_ADOPTION_DATE_COLUMN_NAME = "GAI_ADOPTION_DT";
	public final static String IN_SERVICE_DATE_COLUMN_NAME = "IN_SERVICE_DT";
	public final static String OUT_OF_SERVICE_DATE_COLUMN_NAME = "OUT_OF_SERVICE_DT";
	public final static String LIST_NAME_COLUMN_NAME = "LIST_NM";
	public final static String CONTEXT_NAME_COLUMN_NAME = "CONTEXT_NM";
	public final static String ITEM_CODE_COLUMN_NAME = "ITEM_CD";
	public final static String SHORT_DESCRIPTION_COLUMN_NAME = "SHORT_DESC";
	public final static String LONG_DESCRIPTION_COLUMN_NAME = "LONG_DESC";
	public final static String NOTES_COLUMN_NAME = "NOTES_TXT";

	
	
	public final static String SUBJECT_ID_PARAMETER_NAME = "subjectId";
	public final static String GAI_ADOPTION_DATE_PARAMETER_NAME = "gaiAdoptionDate";
	public final static String IN_SERVICE_DATE_PARAMETER_NAME = "inServiceDate";
	public final static String OUT_OF_SERVICE_DATE_PARAMETER_NAME = "outOfServiceDate";
	public final static String LIST_NAME_PARAMETER_NAME = "listName";
	public final static String CONTEXT_NAME_PARAMETER_NAME = "contextName";
	public final static String ITEM_CODE_PARAMETER_NAME = "itemCode";
	public final static String SHORT_DESCRIPTION_PARAMETER_NAME = "shortDescription";
	public final static String LONG_DESCRIPTION_PARAMETER_NAME = "longDescription";
	public final static String NOTES_PARAMETER_NAME = "noteText";

	
	public static final String FIND_ITEM_BY_KEY_SQL = "SELECT SUBJECT_ID,GAI_ADOPTION_DT,IN_SERVICE_DT,OUT_OF_SERVICE_DT,LIST_NM,CONTEXT_NM,ITEM_CD,SHORT_DESC,LONG_DESC,NOTES_TXT FROM DBO.VW_REFERENCE_DATA";
	
	public static final String CHECK_DEPENDENCY_SQL = "select count(*) from dbo.vw_Reference_Data ";
	
	public static final String FIND_ALL_LISTS_SQL = "SELECT distinct CONTEXT_NM,LIST_NM  FROM DBO.VW_REFERENCE_DATA WHERE GAI_ADOPTION_DT <= :gaiAdoptionDate  AND  OUT_OF_SERVICE_DT >= :gaiAdoptionDate AND IN_SERVICE_DT <= :inServiceDate  AND  OUT_OF_SERVICE_DT >= :inServiceDate AND CONTEXT_NM IN (:contextName) ORDER BY LIST_NM, CONTEXT_NM";
	
	
	
		
}
