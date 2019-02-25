package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class WMCTest extends BaseTest {

	private static Map<String, CKNumber> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/wmc");
	}

	@Test
	public void countAllBranchInstructions() {
		Assert.assertEquals(2, report.get("wmc.CC1").getWmc());
		Assert.assertEquals(3, report.get("wmc.CC2").getWmc());
		Assert.assertEquals(10, report.get("wmc.CC3").getWmc());
	}

	@Test
	public void methodLevel() {

		CKNumber a = report.get("wmc.CC1");
		Assert.assertEquals(1, a.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(1, a.getMethod("m2/0").get().getWmc());

		CKNumber b = report.get("wmc.CC2");
		Assert.assertEquals(2, b.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(1, b.getMethod("m2/0").get().getWmc());

		CKNumber c = report.get("wmc.CC3");
		Assert.assertEquals(10, c.getMethod("m1/0").get().getWmc());
	}
}
