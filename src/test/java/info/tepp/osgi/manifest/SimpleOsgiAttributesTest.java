package info.tepp.osgi.manifest;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static info.tepp.osgi.manifest.OsgiAttributes.Name.*;
import static org.junit.Assert.assertEquals;

public class SimpleOsgiAttributesTest {

    OsgiAttributes attributes = new OsgiAttributes();

    @Test
    public void canGetBundleName() {
        attributes.put(Bundle_Name, "Bundle name");
        assertEquals("Bundle name", attributes.getBundleName());
    }

    @Test
    public void canSetBundleName() {
        attributes.setBundleName("Bundle name");
        assertEquals("Bundle name", attributes.getBundleName());
    }

    @Test
    public void canGetBundleContactAddress() {
        attributes.put(Bundle_ContactAddress, "2400 Oswego Road, Austin, TX 74563");
        assertEquals("2400 Oswego Road, Austin, TX 74563", attributes.getBundleContactAddress());
    }

    @Test
    public void canSetBundleContactAddress() {
        attributes.setBundleContactAddress("2400 Oswego Road, Austin, TX 74563");
        assertEquals("2400 Oswego Road, Austin, TX 74563", attributes.getBundleContactAddress());
    }

    @Test
    public void canGetBundleCopyright() {
        attributes.put(Bundle_Copyright, "OSGi (c) 2002");
        assertEquals("OSGi (c) 2002", attributes.getBundleCopyright());
    }

    @Test
    public void canSetBundleCopyright() {
        attributes.setBundleCopyright("OSGi (c) 2002");
        assertEquals("OSGi (c) 2002", attributes.getBundleCopyright());
    }

    @Test
    public void canGetBundleDescription() {
        attributes.put(Bundle_Description, "Network Firewall");
        assertEquals("Network Firewall", attributes.getBundleDescription());
    }

    @Test
    public void canSetBundleDescription() {
        attributes.setBundleDescription("Network Firewall");
        assertEquals("Network Firewall", attributes.getBundleDescription());
    }

    @Test
    public void canGetBundleVendor() {
        attributes.put(Bundle_Vendor, "OSGi Alliance");
        assertEquals("OSGi Alliance", attributes.getBundleVendor());
    }

    @Test
    public void canSetBundleVendor() {
        attributes.setBundleVendor("OSGi Alliance");
        assertEquals("OSGi Alliance", attributes.getBundleVendor());
    }

    @Test
    public void canSetImportPackage() {
		List<String> valueList=new ArrayList<>();
		valueList.add("val1;version=\"1.0.0\"");
		valueList.add("val2;version=\"1.0.0\"");
		valueList.add("val3;version=\"1.0.0\"");
        attributes.setImportPackage(valueList);
		assertEquals("val1;version=\"1.0.0\",\r\n val2;version=\"1.0.0\",\r\n val3;version=\"1.0.0\"", attributes.getValue(OsgiAttributes.Name.Import_Package));
		List<String> result=attributes.getImportPackage();
		assertEquals("Returned list of values not of expected size",3,result.size());
		assertEquals("val1;version=\"1.0.0\"",result.get(0));
		assertEquals("val2;version=\"1.0.0\"",result.get(1));
		assertEquals("val3;version=\"1.0.0\"",result.get(2));		
    }

    @Test
    public void canSetRequireBundle() {
		List<String> valueList=new ArrayList<>();
		valueList.add("val1;version=\"1.0.0\"");
		valueList.add("val2;version=\"1.0.0\"");
		valueList.add("val3;version=\"1.0.0\"");
        attributes.setRequireBundle(valueList);
		assertEquals("val1;version=\"1.0.0\",\r\n val2;version=\"1.0.0\",\r\n val3;version=\"1.0.0\"", attributes.getValue(OsgiAttributes.Name.Require_Bundle));		
		List<String> result=attributes.getRequireBundle();
		assertEquals(3,result.size());
		assertEquals("val1;version=\"1.0.0\"",result.get(0));
		assertEquals("val2;version=\"1.0.0\"",result.get(1));
		assertEquals("val3;version=\"1.0.0\"",result.get(2));		
	}
}