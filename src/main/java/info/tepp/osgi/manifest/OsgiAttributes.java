package info.tepp.osgi.manifest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OsgiAttributes extends Attributes {
    //@formatter:off
    private final String reqBundle = "[a-z][a-z0-9\\.\\_\\-]+(;bundle\\-version\\=\\\"[\\[\\(]?([0-9]+\\.[0-9]+[0-9a-zA-Z\\.\\_]*)(\\,([0-9]+\\.[0-9]+[0-9a-zA-Z\\.\\_]*))?[\\]\\)]?\\\")?(;resolution\\:\\=\\\"optional\\\")?";
    private final String impPkg =    "[a-z][a-z0-9\\.\\_\\-]+(;version\\=\\\"[\\[\\(]?([0-9]+\\.[0-9]+[0-9a-zA-Z\\.\\_]*)(\\,([0-9]+\\.[0-9]+[0-9a-zA-Z\\.\\_]*))?[\\]\\)]?\\\")?(;resolution\\:\\=\\\"optional\\\")?";
    private final String expPkg =    "[a-z][a-z0-9\\.\\_\\-]+(;version\\=\\\"[0-9]+\\.[0-9]+[0-9a-zA-Z\\.\\_]*\\\")?";

    private Attributes attribs;

    /** Default-Order for written manifest-entries */
    private static List<String> keyOrder = Arrays.asList(new String[] {
       //"Manifest-Version",
       Name.Bundle_ManifestVersion.toString(),
       Name.Bundle_Name.toString(),
       Name.Bundle_SymbolicName.toString(),
       Name.Bundle_Version.toString(),
       Name.Bundle_Vendor.toString(),
       Name.Bundle_License.toString(),
       Name.Bundle_ContactAddress.toString(),
       Name.Bundle_Description.toString(),
       Name.Bundle_Copyright.toString(),
       Name.Bundle_RequiredExecutionEnvironment.toString(),
       Name.Require_Capability.toString(),
       Name.Bundle_Activator.toString(),
       Name.Bundle_ActivationPolicy.toString(),
       Name.Bundle_Localization.toString(),
       Name.Bundle_ClassPath.toString(),
       Name.Eclipse_BuddyPolicy.toString(),
       Name.Eclipse_RegisterBuddy.toString(),
       Name.Eclipse_BundleShape.toString(),
       Name.Eclipse_PlatformFilter.toString(),
       Name.Require_Bundle.toString(),
       Name.Import_Package.toString(),
       Name.Export_Package.toString(),
       Name.Export_Service.toString()
    });
    //@formatter:on

    //@formatter:off
    /**
     * Returns value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Name Bundle-Name} attribute.
     */
    public String getBundleName() {
        return getValue(Name.Bundle_Name);
    }

    /**
     * Sets value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Name Bundle-Name} attribute.
     */
    public void setBundleName(final String name) {
        put(Name.Bundle_Name, name);
    }

    /**
     * Returns value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_ContactAddress
     * Bundle-ContactAddress} attribute.
     */
    public String getBundleContactAddress() {
        return getValue(Name.Bundle_ContactAddress);
    }

    /**
     * Sets value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_ContactAddress
     * Bundle-ContactAddress} attribute.
     */
    public void setBundleContactAddress(final String contactAddress) {
        put(Name.Bundle_ContactAddress, contactAddress);
    }

    /**
     * Returns value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Copyright
     * Bundle-Copyright} attribute.
     */
    public String getBundleCopyright() {
        return getValue(Name.Bundle_Copyright);
    }

    /**
     * Sets value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Copyright
     * Bundle-Copyright} attribute.
     */
    public void setBundleCopyright(final String copyright) {
        put(Name.Bundle_Copyright, copyright);
    }

    /**
     * Returns value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Description
     * Bundle-Description} attribute.
     */
    public String getBundleDescription() {
        return getValue(Name.Bundle_Description);
    }

    /**
     * Gets value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Description
     * Bundle-Description} attribute.
     */
    public void setBundleDescription(final String description) {
        put(Name.Bundle_Description, description);
    }

    /**
     * Returns value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Vendor
     * Bundle-Vendor} attribute.
     */
    public String getBundleVendor() {
        return getValue(Name.Bundle_Vendor);
    }

    /**
     * Sets value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Vendor
     * Bundle-Vendor} attribute.
     */
    public void setBundleVendor(final String vendor) {
        put(Name.Bundle_Vendor, vendor);
    }

    /**
     * Returns value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Require_Bundle} attribute.
     */
    public List<String> getRequireBundle() {
        return getMultilineValue(Name.Require_Bundle, reqBundle);
    }

    /**
     * Sets value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Copyright Bundle-Copyright} attribute.
     */
    public void setRequireBundle(final List<String> bundles) {
        putMultilineValue(Name.Require_Bundle, bundles);
    }

    /**
     * Returns value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Import_Package} attribute.
     */
    public List<String> getImportPackage() {
        return getMultilineValue(Name.Import_Package, impPkg);
    }

    /**
     * Sets value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Copyright Bundle-Copyright} attribute.
     */
    public void setImportPackage(final List<String> packages) {
        putMultilineValue(Name.Import_Package, packages);
    }

    /**
     * Returns value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Export_Package} attribute.
     */
    public List<String> getExportPackage() {
        return getMultilineValue(Name.Export_Package, expPkg);
    }

    /**
     * Sets value of the {@link info.tepp.osgi.manifest.OsgiAttributes.Name#Bundle_Copyright Bundle-Copyright} attribute.
     */
    public void setExportPackage(final List<String> packages) {
        putMultilineValue(Name.Export_Package, packages);
    }

    /**
     * Represents name of an OSGi attribute.
     */
    public static class Name extends java.util.jar.Attributes.Name {

        /*
         * OSGi Alliance (core.osgi) namespace header name.
         * ================================================ */

        /**
         * OSGi bundle attribute - the activation policy of this bundle.
         *
         * <p>The <i>Bundle-ActivationPolicy</i> specifies how the framework should activate the bundle once started.</p>
         */
        public static final Name Bundle_ActivationPolicy = new Name("Bundle-ActivationPolicy");

        /**
         * OSGi bundle attribute - the activator implementation of this bundle.
         *
         * <p>The <i>Bundle-Activator</i> header specifies the name of the class used to start and stop the bundle.</p>
         */
        public static final Name Bundle_Activator = new Name("Bundle-Activator");

        /**
         * OSGi bundle attribute - the comma separated list of category names for this bundle.
         *
         * <p>The <i>Bundle-Category</i> The Bundle-Category header holds a comma-separated list of category names.</p>
         */
        public static final Name Bundle_Category = new Name("Bundle-Category");

        /**
         * OSGi bundle attribute - the classpath of this bundle.
         *
         * <p>The <i>Bundle-ClassPath</i> header defines a comma-separated list of JAR
         * file path names or directories (inside the bundle) containing classes and resources.
         * The period (’.’) specifies the root directory of the bundle’s JAR.
         * The period is also the default.</p>
         */
        public static final Name Bundle_ClassPath = new Name("Bundle-ClassPath");

        /**
         * OSGi bundle attribute - the contact address of this bundle vendor.
         *
         * <p>The <i>Bundle-ContactAddress</i> header provides the contact address of the vendor.</p>
         */
        public static final Name Bundle_ContactAddress = new Name("Bundle-ContactAddress");

        /**
         * OSGi bundle attribute - the copyright of this bundle.
         *
         * <p>The <i>Bundle-Copyright</i> header contains the copyright specification for this bundle.</p>
         *
         * @see #Bundle_Name
         * @see #Bundle_Description
         * @see #Bundle_Vendor
         * @see #Bundle_Icon
         */
        public static final Name Bundle_Copyright = new Name("Bundle-Copyright");

        /**
         * OSGi bundle attribute - the description of this bundle.
         *
         * <p>The <i>Bundle-Description</i> header defines a short description of this bundle.</p>
         *
         * @see #Bundle_Name
         * @see #Bundle_Vendor
         * @see #Bundle_Copyright
         * @see #Bundle_Icon
         */
        public static final Name Bundle_Description = new Name("Bundle-Description");

        /**
         * OSGi bundle attribute - the url to documentation of this bundle.
         *
         * <p>The <i>Bundle-DocURL</i> headers must contain a URL pointing to documentation about this bundle.</p>
         */
        public static final Name Bundle_DocURL = new Name("Bundle-DocURL");

        /**
         * OSGi bundle attribute - the bundle symbolic name.
         *
         * <p>The optional {@code Bundle-Icon} header provides a list of URLs
         * to icons representing this bundle in different sizes. The following attribute is permitted:</p>
         * <ul>
         *     <li><code>size</code> – (integer) Specifies the size of the icon in pixels horizontal.
         *     It is recommended to always include a 64x64 icon.</li>
         * </ul>
         * <p>The URLs are interpreted as relative to the bundle.
         * That is, if a URL with a scheme is provided, then this is taken as an absolute URL.
         * Otherwise, the path points to an entry in the JAR file, taking any attached fragments
         * into account. Implementations that want to use this header should at least support the
         * Portable Network Graphics (PNG) format, see
         * <a href="http://www.w3.org/TR/2003/REC-PNG-20031110/">Portable Network Graphics (PNG) Specification
         * (Second Edition)</a>.</p>
         *
         * @see #Bundle_Name
         * @see #Bundle_Description
         * @see #Bundle_Vendor
         * @see #Bundle_Copyright
         */
        public static final Name Bundle_Icon = new Name("Bundle-Icon");

        /**
         * OSGi bundle attribute - the license of this bundle.
         *
         * <p>The <i>Bundle-License</i> header provides an optional machine readable form of license information.
         * The purpose of this header is to automate some of the license processing required by many organizations
         * like for example license acceptance before a bundle is used. The header is structured to provide the use
         * of unique license naming to merge acceptance requests, as well as links to human readable information
         * about the included licenses. This header is purely informational for management agents and must not be
         * processed by the OSGi Framework.</p>
         *
         * <p>The syntax for this header is as follows:</p>
         * <pre>{@code
         *    Bundle-License ::= ’<<EXTERNAL>>’ |
         *                       ( license ( ’,’ license ) * )
         *    license        ::= name ( ’;’ license-attr ) *
         *    license-attr   ::= description | link
         *    description    ::= ’description’ ’=’ string
         *    link           ::= ’link’ ’=’ <url>
         * }</pre>
         *
         * <p>This header has the following attributes:</p>
         * <ul>
         *     <li><em>name</em> – Provides a globally unique name for this license, preferably world wide, but it
         *     should at least be unique with respect to the other clauses. The magic name {@literal <<EXTERNAL>>}
         *     is used to indicate that this artifact does not contain any license information but that licensing
         *     information is provided in some other way. This is also the default contents of this header. Clients
         *     of this bundle can assume that licenses with the same name refer to the same license. This can for
         *     example be used to minimize the click through licenses. This name should be the canonical URL of the
         *     license, it must not be localized by the translator. This URL does not have to exist but must not be
         *     used for later versions of the license. It is recommended to use URLs from
         *     <a href="http://www.opensource.org/">Open Source Initiative</a>.
         *     Other licenses should use the following structure, but this is not mandated:
         *     <br>{@code http://<domain-name>/licenses/<license-name>-<version>.<extension>}</li>
         *
         *     <li><em>description</em> – (optional) Provide the description of the license.
         *     This is a short description that is usable in a list box on a UI to select more
         *     information about the license.</li>
         *
         *     <li><em>link</em> – (optional) Provide a URL to a page that defines or explains the license.
         *     If this link is absent, the name field is used for this purpose. The URL is relative to the
         *     root of the bundle. That is it is possible to refer to a file inside the bundle.</li>
         * </ul>
         *
         * <p>If the {@literal Bundle-License} statement is absent, then this does not mean that the bundle
         * is not licensed. Licensing could be handled outside the bundle and the {@literal <<EXTERNAL>>} form
         * should be assumed. This header is informational and may not have any legal bearing. Consult a lawyer
         * before using this header to automate licensing processing.</p>
         */
        public static final Name Bundle_License = new Name("Bundle-License");

        /**
         * OSGi bundle attribute - the localization of this bundle.
         *
         * <p>The <i>Bundle-Localization</i> header contains the location in the bundle where localization files
         * can be found. The default value is {@literal OSGI-INF/l10n/bundle}. Translations are by default
         * therefore {@literal OSGI-INF/l10n/bundle_de.properties}, {@literal OSGI-INF/l10n/bundle_nl.properties},
         * etc.</p>
         */
        public static final Name Bundle_Localization = new Name("Bundle-Localization");

        /**
         * OSGi bundle attribute - the OSGi manifest version of this bundle.
         *
         * <p>The <i>Bundle-ManifestVersion</i> header defines that the bundle follows the rules of this specification.
         * The Bundle-ManifestVersion header determines whether the bundle follows the rules of this specification.
         * It is 1 (the default) for Release 3 Bundles, 2 for Release 4 and later.
         * Future version of the OSGi Service Platform can define higher numbers for this header.</p>
         */
        public static final Name Bundle_ManifestVersion = new Name("Bundle-ManifestVersion");

        /**
         * OSGi bundle attribute - the human-readable name of this bundle.
         *
         * <p>The <i>Bundle-Name</i> header defines a readable name for this bundle.
         * This should be a short, human-readable name that can contain spaces.</p>
         *
         * @see #Bundle_SymbolicName
         * @see #Bundle_Description
         * @see #Bundle_Copyright
         * @see #Bundle_Icon
         */
        public static final Name Bundle_Name = new Name("Bundle-Name");

        /**
         * OSGi bundle attribute - native code libraries specification of this bundle.
         *
         * <p>The <i>Bundle-NativeCode</i> header contains a specification of native code libraries contained
         * in this bundle.</p>
         * <!-- See Loading Native Code Libraries on page 60. -->
         */
        public static final Name Bundle_NativeCode = new Name("Bundle-NativeCode");

        /**
         * OSGi bundle attribute - execution environments of this bundle.
         *
         * The <i>Bundle-RequiredExecutionEnvironment</i> contains a comma-separated list of execution environments
         * that must be present on the Service Platform. <!-- See Execution Environment on page 35. -->
         * <em>This header is deprecated.</em>
         * @deprecated This header is deprecated in R4.3 in favor of more general capabilities feature
         * @see #Require_Capability
         */
        @Deprecated
        public static final Name Bundle_RequiredExecutionEnvironment = new Name("Bundle-RequiredExecutionEnvironment");

        /**
         * OSGi bundle attribute - non-localizable name of this bundle.
         *
         * <p>The <i>Bundle-SymbolicName</i> header specifies a non-localizable name for this bundle.
         * The bundle symbolic name together with a version must identify a unique bundle though it
         * can be installed multiple times in a framework. The bundle symbolic name should be based
         * on the reverse domain name convention<!--, see Bundle-SymbolicName on page 40 -->.
         * <em>This header must be set.</em></p>
         *
         * @see #Bundle_Name
         */
        public static final Name Bundle_SymbolicName = new Name("Bundle-SymbolicName");

        /**
         * OSGi bundle attribute - update url of this bundle.
         *
         * <p>The <i>Bundle-UpdateLocation</i> header specifies a URL where an update for this bundle should come from.
         * If the bundle is updated, this location should be used, if present, to retrieve the updated JAR file.</p>
         */
        public static final Name Bundle_UpdateLocation = new Name("Bundle-UpdateLocation");

        /**
         * OSGi bundle attribute - update url of this bundle.
         *
         * <p>The <i>Bundle-Vendor</i> header contains a human-readable description of the bundle vendor.</p>
         *
         * @see #Bundle_Name
         * @see #Bundle_Description
         * @see #Bundle_Copyright
         * @see #Bundle_Icon
         */
        public static final Name Bundle_Vendor = new Name("Bundle-Vendor");

        /**
         * OSGi bundle attribute - version of this bundle.
         *
         * <p>The <i>Bundle-Version</i> header specifies the version of this bundle.
         * <!-- TODO: See Version on page 29. -->
         * The default value is 0.0.0</p>
         *
         * @see #Bundle_SymbolicName
         */
        public static final Name Bundle_Version = new Name("Bundle-Version");

        /**
         * OSGi bundle attribute - dynamic imports of this bundle.
         *
         * <p>The <i>DynamicImport-Package</i> header contains a comma-separated list of package names that should
         * be dynamically imported when needed.
         * <!-- See Dynamic Import Package on page 54. --></p>
         */
        public static final Name DynamicImport_Package = new Name("DynamicImport-Package");

        /**
         * OSGi bundle attribute - exported (public) packages of this bundle.
         *
         * <p>The <i>Export-Package</i> header contains a declaration of exported packages.
         * <!-- See Export-Package on page 42. --></p>
         */
        public static final Name Export_Package = new Name("Export-Package");

        /**
         * OSGi bundle attribute - exported (public) services of this bundle.
         *
         * @deprecated Deprecated as of 1.2.
         */
        @Deprecated
        public static final Name Export_Service = new Name("Export-Service");

        /**
         * OSGi bundle attribute - fragment host of this bundle.
         *
         * <p>The <i>Fragment-Host</i> header defines the host bundles for this fragment.
         * <!--See Fragment-Host on page 70--></p>
         */
        public static final Name Fragment_Host = new Name("Fragment-Host");

        /**
         * OSGi bundle attribute - imported packages of this bundle.
         *
         * <p>The <i>Import-Package</i> header declares the imported packages for this bundle.
         * <!-- See Import-Package Header on page 41. --></p>
         */
        public static final Name Import_Package = new Name("Import-Package");

        /**
         * OSGi bundle attribute - imported services of this bundle.
         *
         * @deprecated Deprecated as of 1.2.
         */
        @Deprecated
        public static final Name Import_Service = new Name("Import-Service");

        /**
         * OSGi bundle attribute - capabilities provided by this bundle.
         *
         * <p>Specifies that a bundle provides a set of capabilities<!--, see Dependencies on page 32-->.</p>
         */
        public static final Name Provided_Capability = new Name("Provided-Capability");

        /**
         * OSGi bundle attribute - dependency of this bundle.
         *
         * <p>The <i>Require-Bundle</i> header specifies that all exported packages from another
         * bundle must be imported, effectively requiring the public interface of another bundle.
         * <!--See Require-Bundle on page 66--></p>
         *
         * @see #Import_Package
         * @see #Require_Capability
         */
        public static final Name Require_Bundle = new Name("Require-Bundle");

        /**
         * OSGi bundle attribute - capabilities this bundle requires.
         *
         * <p>Specifies that a bundle requires other bundles to provide a
         * capability<!--, see Dependencies on page 32-->.</p>
         *
         * @see #Bundle_RequiredExecutionEnvironment
         * @see #Provided_Capability
         * @see #Import_Package
         */
        public static final Name Require_Capability = new Name("Require-Capability");

        /*
         * Eclipse Foundation namespace header names.
         * ========================================== */



        public static final Name Eclipse_BuddyPolicy    = new Name("Eclipse-BuddyPolicy");

        public static final Name Eclipse_BundleShape    = new Name("Eclipse-BundleShape");
        public static final Name Eclipse_ExtensibleAPI  = new Name("Eclipse-ExtensibleAPI");
        public static final Name Eclipse_PlatformFilter = new Name("Eclipse-PlatformFilter");
        public static final Name Eclipse_RegisterBuddy  = new Name("Eclipse-RegisterBuddy");
        /*
         * SpringSource namespace header names.
         * ==================================== */



        public static final Name Import_Bundle                    = new Name("Import-Bundle");

        public static final Name Import_Library                   = new Name("Import-Library");
        public static final Name Module_Scope                     = new Name("Module-Scope");
        public static final Name Module_Type                      = new Name("Module-Type");
        public static final Name Web_ContextPath                  = new Name("Web-ContextPath");
        public static final Name Web_DispatcherServletUrlPatterns = new Name("Web-DispatcherServletUrlPatterns");
        public static final Name Web_FilterMappings               = new Name("Web-FilterMappings");
        /*
         * aQute namespace header names.
         * ==================================== */

        public static final Name Include_Resource               = new Name("Include-Resource");

        public Name(final String s) {
            super(s);
        }


    }
//@formatter:on
    public OsgiAttributes() {
        super();
    }

    public OsgiAttributes(final int i) {
        super(i);
    }

    public OsgiAttributes(final Attributes attributes) {
        super(attributes);
        this.attribs = attributes;
    }

    /** Typesafe put operation */
    public String put(final OsgiAttributes.Name name, final String value) {
        if (attribs != null)
            attribs.put(name, value);
        return (String) super.put(name, value);
    }

    /** Typesafe put operation */
    public String putValue(final OsgiAttributes.Name name, final String value) {
        if (attribs != null)
            attribs.put(name, value);
        return (String) super.put(name, value);
    }

    protected List<String> getMultilineValue(final OsgiAttributes.Name name, final String entryPattern) {
        final String value = getValue(name);
        if (value == null)
            return null;
        final List<String> result = new ArrayList<>();
        final Matcher matcher = Pattern.compile(entryPattern).matcher(value);
        while (matcher.find()) {
            final String entry = matcher.group();
            result.add(entry.trim());
        }
        return result;
    }

    protected String putMultilineValue(final OsgiAttributes.Name name, final List<String> value) {
        final StringBuffer buf = new StringBuffer();
        boolean atFirst = true;
        for (final String line : value) {
            if (atFirst) {
                buf.append(line);
                atFirst = false;
            } else {
                buf.append(",\r\n ");
                buf.append(line);
            }
        }
        return putValue(name, buf.toString());
    }

    /*
     * Writes the current attributes to the specified data output stream, make sure to write out the MANIFEST_VERSION or SIGNATURE_VERSION attributes first. XXX
     * Need to handle UTF8 values and break up lines longer than 72 bytes
     */
    public static void writeMainAttr(final DataOutputStream os, final Attributes attr) throws IOException {
        // write out the *-Version header first, if it exists
        String vername = Name.MANIFEST_VERSION.toString();
        String version = attr.getValue(vername);
        if (version == null) {
            vername = Name.SIGNATURE_VERSION.toString();
            version = attr.getValue(vername);
        }

        if (version != null) {
            os.writeBytes(vername + ": " + version + "\r\n");
        }

        // write out all attributes except for the version
        // we wrote out earlier
        if (version != null)
            writeAttr(os, attr, Arrays.asList(new String[] { vername }));
        else
            os.writeBytes("\r\n"); // final empty line
    }

    /*
     * Writes the current attributes to the specified data output stream. Use the defined key-order of keyOrder-set. XXX Need to handle UTF8 values and break up
     * lines longer than 72 bytes
     */
    public static void writeAttr(final DataOutputStream os, final Attributes attr, final List<String> suppressedKeys) throws IOException {
        // at first all default-keys defined in keyOrder, if they exists
        for (final String attrKey : keyOrder) {
            if (attr.containsKey(new Name(attrKey))) {
                if (suppressedKeys == null || !suppressedKeys.contains(attrKey)) {
                    final StringBuffer buffer = new StringBuffer(attrKey);
                    buffer.append(": ");

                    String value = (String) attr.getValue(attrKey);
                    if (value != null) {
                        final byte[] vb = value.getBytes("UTF8");
                        value = new String(vb, 0, 0, vb.length);
                    }
                    buffer.append(value);

                    buffer.append("\r\n");
                    os.writeBytes(buffer.toString());
                }
            }
        }
        // now all remaining keys in non-deterministic order
        final Iterator<Map.Entry<Object, Object>> it = attr.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry<Object, Object> e = it.next();
            final String key = (e.getKey()).toString();
            if (!keyOrder.contains(key)) { // all other keys are already written
                if (suppressedKeys == null || !suppressedKeys.contains(key)) {
                    final StringBuffer buffer = new StringBuffer(key);
                    buffer.append(": ");

                    String value = (String) e.getValue();
                    if (value != null) {
                        final byte[] vb = value.getBytes("UTF8");
                        value = new String(vb, 0, 0, vb.length);
                    }
                    buffer.append(value);

                    buffer.append("\r\n");
                    os.writeBytes(buffer.toString());
                }
            }
        }
        os.writeBytes("\r\n"); // final empty line
    }
}
