package info.tepp.osgi.manifest;

import static info.tepp.osgi.manifest.OsgiAttributes.Name.Bundle_ContactAddress;
import static info.tepp.osgi.manifest.OsgiAttributes.Name.Bundle_Copyright;
import static info.tepp.osgi.manifest.OsgiAttributes.Name.Bundle_Description;
import static info.tepp.osgi.manifest.OsgiAttributes.Name.Bundle_Name;
import static info.tepp.osgi.manifest.OsgiAttributes.Name.Bundle_Vendor;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
        final List<String> valueList = new ArrayList<>();
        valueList.add("pkg1;version=\"1.0.0\"");
        valueList.add("pkg2;version=\"[1.0.0,2.0.0.qualifier)\"");
        valueList.add("pkg3;version=\"[1.0.0,2.0.0)\"");
        valueList.add("pkg4");
        valueList.add("pkg5");
        attributes.setImportPackage(valueList);
        assertEquals("pkg1;version=\"1.0.0\",\r\n pkg2;version=\"[1.0.0,2.0.0.qualifier)\",\r\n pkg3;version=\"[1.0.0,2.0.0)\",\r\n pkg4,\r\n pkg5",
                attributes.getValue(OsgiAttributes.Name.Import_Package));
        final List<String> result = attributes.getImportPackage();
        assertEquals("Returned list of values not of expected size", 5, result.size());
        assertEquals("pkg1;version=\"1.0.0\"", result.get(0));
        assertEquals("pkg2;version=\"[1.0.0,2.0.0.qualifier)\"", result.get(1));
        assertEquals("pkg3;version=\"[1.0.0,2.0.0)\"", result.get(2));
        assertEquals("pkg4", result.get(3));
        assertEquals("pkg5", result.get(4));
    }

    @Test
    public void canSetExportPackage() {
        final List<String> valueList = new ArrayList<>();
        valueList.add("pkg1;version=\"1.0.0\"");
        valueList.add("pkg2;version=\"2.0.0.qualifier\"");
        valueList.add("pkg3");
        valueList.add("pkg4");
        attributes.setExportPackage(valueList);
        assertEquals("pkg1;version=\"1.0.0\",\r\n pkg2;version=\"2.0.0.qualifier\",\r\n pkg3,\r\n pkg4",
                attributes.getValue(OsgiAttributes.Name.Export_Package));
        final List<String> result = attributes.getExportPackage();
        assertEquals("Returned list of values not of expected size", 4, result.size());
        assertEquals("pkg1;version=\"1.0.0\"", result.get(0));
        assertEquals("pkg2;version=\"2.0.0.qualifier\"", result.get(1));
        assertEquals("pkg3", result.get(2));
        assertEquals("pkg4", result.get(3));
    }

    @Test
    public void canSetRequireBundle() {
        final List<String> valueList = new ArrayList<>();
        valueList.add("info.bundle1;bundle-version=\"1.0.0\"");
        valueList.add("info.bundle2;bundle-version=\"1.0.0\";resolution:=\"optional\"");
        valueList.add("info.bundle3;bundle-version=\"[1.0.0,2.0.0)\"");
        valueList.add("info.bundle4;resolution:=\"optional\"");
        valueList.add("info.bundle5");
        attributes.setRequireBundle(valueList);
        assertEquals(
                "info.bundle1;bundle-version=\"1.0.0\",\r\n info.bundle2;bundle-version=\"1.0.0\";resolution:=\"optional\",\r\n info.bundle3;bundle-version=\"[1.0.0,2.0.0)\",\r\n info.bundle4;resolution:=\"optional\",\r\n info.bundle5",
                attributes.getValue(OsgiAttributes.Name.Require_Bundle));
        final List<String> result = attributes.getRequireBundle();
        assertEquals(5, result.size());
        assertEquals("info.bundle1;bundle-version=\"1.0.0\"", result.get(0));
        assertEquals("info.bundle2;bundle-version=\"1.0.0\";resolution:=\"optional\"", result.get(1));
        assertEquals("info.bundle3;bundle-version=\"[1.0.0,2.0.0)\"", result.get(2));
        assertEquals("info.bundle4;resolution:=\"optional\"", result.get(3));
        assertEquals("info.bundle5", result.get(4));
    }

    @Test
    public void testParseReqBundle() {
        attributes.putValue(OsgiAttributes.Name.Require_Bundle,
                "info.bundle1;bundle-version=\"1.0.0\",\r\n info.bundle2;bundle-version=\"[1.0.0,2.0.0)\";resolution:=\"optional\",\r\n info.bundle3;bundle-version=\"[1.0.0.ABC,2.0.0_xyz)\",\r\n info.bundle4;resolution:=\"optional\",\r\n info.bundle5");
        final List<String> result1 = attributes.getRequireBundle();
        assertEquals(5, result1.size());
        attributes.putValue(OsgiAttributes.Name.Require_Bundle,
                "info.bundle1;bundle-version=\"1.0.0\",info.bundle2;bundle-version=\"1.0.0\";resolution:=\"optional\",info.bundle3;bundle-version=\"[1.0.0,2.0.0)\",info.bundle4;resolution:=\"optional\",info.bundle5");
        final List<String> result2 = attributes.getRequireBundle();
        assertEquals(5, result2.size());
    }

    @Test
    public void testImpPkg() {
        attributes.putValue(OsgiAttributes.Name.Import_Package,
                "info.pkg1;version=\"1.0.0\",\r\n info.pkg2;version=\"[1.0.0,2.0.0)\";resolution:=\"optional\",\r\n info.pkg3;version=\"[1.0.0.ABC,2.0.0_XYZ)\",\r\n info.pkg4;resolution:=\"optional\",\r\n info.pkg5");
        final List<String> result1 = attributes.getImportPackage();
        assertEquals(5, result1.size());
        attributes.putValue(OsgiAttributes.Name.Import_Package,
                "info.pkg1;version=\"1.0.0\",info.pkg2;version=\"[1.0.0,2.0.0)\";resolution:=\"optional\",info.pkg3;version=\"[1.0.0,2.0.0)\",info.pkg4;resolution:=\"optional\",info.pkg5");
        final List<String> result2 = attributes.getImportPackage();
        assertEquals(5, result2.size());
    }

    @Test
    public void testExpPkg() {
        attributes.putValue(OsgiAttributes.Name.Export_Package, "info.pkg1;version=\"1.0.0\",\r\n info.pkg2,\r\n info.pkg3;version=\"1.0.0\",\r\n info.pkg4");
        final List<String> result1 = attributes.getExportPackage();
        assertEquals(4, result1.size());
        attributes.putValue(OsgiAttributes.Name.Export_Package,
                "info.pkg1;version=\"1.0.0\",info.pkg2,info.pkg3;version=\"1.0.0\",info.pkg4,info.pkg5;version=\"1.0.0_ABC\",info.pkg6;version=\"1.0.0.xyz\"");
        final List<String> result2 = attributes.getExportPackage();
        assertEquals(6, result2.size());
        assertEquals("info.pkg1;version=\"1.0.0\"", result2.get(0));
        assertEquals("info.pkg2", result2.get(1));
        assertEquals("info.pkg3;version=\"1.0.0\"", result2.get(2));
        assertEquals("info.pkg4", result2.get(3));
        assertEquals("info.pkg5;version=\"1.0.0_ABC\"", result2.get(4));
        assertEquals("info.pkg6;version=\"1.0.0.xyz\"", result2.get(5));
    }
}