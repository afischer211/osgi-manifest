package info.tepp.osgi.manifest;

import static info.tepp.osgi.manifest.OsgiAttributes.Name.Bundle_Name;
import static info.tepp.osgi.manifest.OsgiAttributes.Name.Bundle_Version;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import info.tepp.osgi.manifest.OsgiAttributes.Name;

public class SimpleOsgiManifestTest {

    OsgiManifest manifest = new OsgiManifest();

    @Test
    public void testWrite() {
        manifest.getMainAttributes().put(Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Bundle_Version, "1.0.0");
        manifest.getMainAttributes().put(Bundle_Name, "Bundle name");
        manifest.getMainAttributes().put(new Name("Non-OSGi-Key"), "Test");
        final List<String> valueList = new ArrayList<>();
        valueList.add("pkg1;version=\"1.0.0\"");
        valueList.add("pkg2;version=\"1.0.0\"");
        valueList.add("pkg3;version=\"1.0.0\"");
        manifest.getMainAttributes().setExportPackage(valueList);
        manifest.getMainAttributes().setImportPackage(valueList);
        valueList.clear();
        valueList.add("bundle1;version=\"1.0.0\"");
        valueList.add("bundle2;version=\"1.0.0\"");
        manifest.getMainAttributes().setRequireBundle(valueList);
        assertEquals("Bundle name", manifest.getMainAttributes().getBundleName());
        try {
            manifest.write(System.out);
        } catch (final Exception e) {
        }
    }
}