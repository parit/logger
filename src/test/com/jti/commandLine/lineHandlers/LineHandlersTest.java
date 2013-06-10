package com.jti.commandLine.lineHandlers;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jti.commandLine.FileReader;
import com.jti.commandLine.Model;
import com.jti.commandLine.Utils;

public class LineHandlersTest {

	List<String> lines = new ArrayList<String>(5);

	@Before
	public void setup() {
		File file = new File("data/test/test.txt");
		assertTrue(file.exists());
		FileReader fileReader = new FileReader(file);
		for (String str : fileReader) {
			lines.add(str);
		}
	}

	@Test
	public void testActiveLineHandler() {
		LineHandler<Model> lineHandler = new ActiveLineHandler();
		Model model = lineHandler.handle(lines.get(0));
		assertTrue("6218436".equals(model.getJobId()));
		assertTrue("Backup".equals(model.getType()));
		assertTrue("Active".equals(model.getState()));
		assertTrue(model.getStatus() == null);
		assertTrue("POL-bck-99".equals(model.getPolicy()));
		assertTrue("SCH-FUL-WY".equals(model.getSchedule()));
		assertTrue("swigva01-bck-99".equals(model.getClient()));
		assertTrue("swigva01-bck-99".equals(model.getDestMediaServer()));
		assertTrue("3/1/2013 9:00:14 AM".equals(model.getStarted()));
		assertTrue("swigva01-bck-99-hcart3-robot-tld-0".equals(model
				.getDestStUnit()));
		assertTrue(model.getEnded() == null);
		assertTrue("386864896".equals(model.getKilobytes()));
		assertTrue("68195".equals(model.getkBperSec()));
	}

	@Test
	public void testDoneLineHandler() {
		LineHandler<Model> lineHandler = new DoneLineHandler();
//		Model model = lineHandler.handle(lines.get(1));
		Model model = lineHandler.handle("6201740         Backup             Done      0                       POL-SQL-DY-INC                                    gvapiq01                              2/26/2013 10:36:04 PM                                                    2/26/2013 10:38:24 PM                                          ");
		assertTrue("6218437".equals(model.getJobId()));
		assertTrue("Backup".equals(model.getType()));
		assertTrue("Done".equals(model.getState()));
		assertTrue("96".equals(model.getStatus()));
		assertTrue("POL-bck-99".equals(model.getPolicy()));
		assertTrue("SCH-FUL-WY".equals(model.getSchedule()));
		assertTrue("swigva01-bck-99".equals(model.getClient()));
		assertTrue("swigva01-bck-99".equals(model.getDestMediaServer()));
		assertTrue("3/1/2013 9:00:14 AM".equals(model.getStarted()));
		assertTrue("swigva01-bck-99-hcart3-robot-tld-1".equals(model
				.getDestStUnit()));
		assertTrue("3/1/2013 10:01:32 AM".equals(model.getEnded()));
		assertTrue("193562368".equals(model.getKilobytes()));
		assertTrue("32542".equals(model.getkBperSec()));
	}

	@Test
	public void testJunkLineHandler() {
		LineHandler<Model> lineHandler = new JunkLineHandler();
		Model model = lineHandler.handle(lines.get(2));
		assertTrue("6218432".equals(model.getJobId()));
		assertTrue(model.getType() == null);
		assertTrue("".equals(model.getState()));
		assertTrue(model.getStatus() == null);
		assertTrue(model.getPolicy() == null);
		assertTrue(model.getSchedule() == null);
		assertTrue(model.getClient() == null);
		assertTrue(model.getDestMediaServer() == null);
		assertTrue(model.getStarted() == null);
		assertTrue(model.getDestStUnit() == null);
		assertTrue(model.getEnded() == null);
		assertTrue(model.getKilobytes() == null);
		assertTrue(model.getkBperSec() == null);
	}

	@Test
	public void testQueuedLineHandler() {
		LineHandler<Model> lineHandler = new QueuedLineHandler();
		Model model = lineHandler.handle(lines.get(3));
		assertTrue("6218440".equals(model.getJobId()));
		assertTrue("Backup".equals(model.getType()));
		assertTrue("Queued".equals(model.getState()));
		assertTrue(model.getStatus() == null);
		assertTrue("POL-bck-99".equals(model.getPolicy()));
		assertTrue("SCH-FUL-WY".equals(model.getSchedule()));
		assertTrue("swigva01-bck-99".equals(model.getClient()));
		assertTrue(model.getDestMediaServer() == null);
		assertTrue("3/1/2013 9:00:14 AM".equals(model.getStarted()));
		assertTrue(model.getDestStUnit() == null);
		assertTrue(model.getEnded() == null);
		assertTrue(model.getKilobytes() == null);
		assertTrue(model.getkBperSec() == null);
	}

	@Test
	public void testWaitingLineHandler() {
		LineHandler<Model> lineHandler = new WaitingLineHandler();
		Model model = lineHandler.handle(lines.get(4));
		assertTrue("6202767".equals(model.getJobId()));
		assertTrue(model.getType() == null);
		assertTrue("Waiting-to-Retry".equals(model.getState()));
		assertTrue("50".equals(model.getStatus()));
		assertTrue(model.getPolicy() == null);
		assertTrue(model.getSchedule() == null);
		assertTrue(model.getClient() == null);
		assertTrue(model.getDestMediaServer() == null);
		assertTrue(model.getStarted() == null);
		assertTrue(model.getDestStUnit() == null);
		assertTrue(model.getEnded() == null);
		assertTrue(model.getKilobytes() == null);
		assertTrue(model.getkBperSec() == null);
	}

	@Test
	public void testStringToDate() {
		String dateStr = "02/28/2013 10:05 PM";
		assertTrue(dateStr.equals(Utils.toString(Utils.toDate(dateStr))));
	}
} // LineHandlersTest
