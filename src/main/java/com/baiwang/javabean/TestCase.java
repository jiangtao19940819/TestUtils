package com.baiwang.javabean;
import java.util.Map;
public class TestCase {
	private int caseId;
	private boolean caseStatus;
	private String apiName;
	private String caseName;
	private String requestUrl;
	private String requestType;
	private String requestHeader;
	private String requestBody;
	private Map<String,String> requestParam;
	private String cheakPoint;
	private Map<String,String> outParam;
	private String testResult;
	private String runTime;
	public int getCaseId() {
		return caseId;
	}
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	public boolean isCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(boolean caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestHeader() {
		return requestHeader;
	}
	public void setRequestHeader(String requestHeader) {
		this.requestHeader = requestHeader;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public Map<String, String> getRequestParam() {
		return requestParam;
	}
	public void setRequestParam(Map<String, String> requestParam) {
		this.requestParam = requestParam;
	}
	public String getCheakPoint() {
		return cheakPoint;
	}
	public void setCheakPoint(String cheakPoint) {
		this.cheakPoint = cheakPoint;
	}
	public Map<String, String> getOutParam() {
		return outParam;
	}
	public void setOutParam(Map<String, String> outParam) {
		this.outParam = outParam;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	
	 
	
}
