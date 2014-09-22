/*
 * Copyright 2013 International Business Machines Corp.
 * 
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ibm.jbatch.tck.tests.jslxml;

import static com.ibm.jbatch.tck.utils.AssertionUtils.assertWithMessage;

import java.util.Properties;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

import org.junit.Before;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ibm.jbatch.tck.utils.JobOperatorBridge;

public class ContextsGetIdTests {

	private JobOperatorBridge jobOp = null;

	/**
	 * @testName: testJobContextGetId
	 * @assertion: Section 7.7.2 JobContext
	 * @test_Strategy: 1. setup a simple job with one step
	 * 				   2. start job  
	 * 				   3. set job exit status equals job id from JobContext in batchlet
	 * 				   4. compare job id 'job1' to job exit status
	 * 
	 * 	<job id="job1" xmlns="http://xmlns.jcp.org/xml/ns/javaee">
	 * 		<step id="step1">
	 *			<batchlet ref="contextsGetIdJobContextTestBatchlet"/>
	 *		</step>
	 *	</job>
	 *
	 * @throws Exception
	 */
	@Test
	@org.junit.Test
	public void testJobContextGetId() throws Exception {

		String METHOD = "testJobContextGetId";

		try {

			String jobId = "job1";

			Reporter.log("starting job");
			JobExecution jobExec = jobOp.startJobAndWaitForResult("contexts_getid_jobcontext", null);
			Reporter.log("Job Status = " + jobExec.getBatchStatus());

			assertWithMessage("job id equals job1", jobId, jobExec.getExitStatus());
			assertWithMessage("Job completed", BatchStatus.COMPLETED, jobExec.getBatchStatus());
			Reporter.log("job completed");
		} catch (Exception e) {
			handleException(METHOD, e);
		}
	}

	/**
	 * @testName: testStepContextGetId
	 * @assertion: Section 7.7.2 StepContext
	 * @test_Strategy: 1. setup a simple job with one step
	 * 				   2. start job 
	 * 				   3. set job exit status equals step id from StepContext in batchlet
	 * 				   4. compare step id 'step1' to job exit status
	 * 
	 * 	<job id="job1" xmlns="http://xmlns.jcp.org/xml/ns/javaee">
	 * 		<step id="step1">
	 *			<batchlet ref="contextsGetIdStepContextTestBatchlet"/>
	 *		</step>
	 *	</job>
	 *
	 * @throws Exception
	 */
	@Test
	@org.junit.Test
	public void testStepContextGetId() throws Exception {

		String METHOD = "testStepContextGetId";

		try {
			String stepId = "step1";

			Reporter.log("starting job");
			JobExecution jobExec = jobOp.startJobAndWaitForResult("contexts_getid_stepcontext", null);
			Reporter.log("Job Status = " + jobExec.getBatchStatus());

			assertWithMessage("job id equals job1", stepId, jobExec.getExitStatus());

			assertWithMessage("Job completed", BatchStatus.COMPLETED, jobExec.getBatchStatus());
			Reporter.log("job completed");
		} catch (Exception e) {
			handleException(METHOD, e);
		}
	}

	private static void handleException(String methodName, Exception e) throws Exception {
		Reporter.log("Caught exception: " + e.getMessage()+"<p>");
		Reporter.log(methodName + " failed<p>");
		throw e;
	}

	public void setup(String[] args, Properties props) throws Exception {

		String METHOD = "setup";

		try {
			jobOp = new JobOperatorBridge();
		} catch (Exception e) {
			handleException(METHOD, e);
		}
	}

	/* cleanup */
	public void  cleanup()
	{		

	}

	@BeforeTest
	@Before
	public void beforeTest() throws ClassNotFoundException {
		jobOp = new JobOperatorBridge(); 
	}

	@AfterTest
	public void afterTest() {
		jobOp = null;
	}
}
