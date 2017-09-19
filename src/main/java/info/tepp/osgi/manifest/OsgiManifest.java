package info.tepp.osgi.manifest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class OsgiManifest extends Manifest {
    private OsgiAttributes attribs;

    public OsgiManifest() {
        super();
        this.attribs = new OsgiAttributes(super.getMainAttributes());
    }

    public OsgiManifest(final InputStream inputStream) throws IOException {
        super(inputStream);
        this.attribs = new OsgiAttributes(super.getMainAttributes());
    }

    public OsgiManifest(final Manifest manifest) {
        super(manifest);
        this.attribs = new OsgiAttributes(super.getMainAttributes());
    }

    @Override
    public OsgiAttributes getMainAttributes() {
        return attribs;
    }

    @Override
    public Map<String, Attributes> getEntries() {
        return super.getEntries();
    }

    /**
     * Writes the Manifest to the specified OutputStream. Attributes.Name.MANIFEST_VERSION must be set in MainAttributes prior to invoking this method.
     *
     * @param out
     *            the output stream
     * @exception IOException
     *                if an I/O error has occurred
     * @see #getMainAttributes
     */
    @Override
    public void write(final OutputStream out) throws IOException {
        final DataOutputStream dos = new DataOutputStream(out);
        // Write out the main attributes for the manifest
        OsgiAttributes.writeMainAttr(dos, getMainAttributes());
        // Now write out the pre-entry attributes
        final Iterator<Map.Entry<String, Attributes>> it = getEntries().entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry<String, Attributes> e = it.next();
            final StringBuffer buffer = new StringBuffer("Name: ");
            String value = e.getKey();
            if (value != null) {
                final byte[] vb = value.getBytes("UTF8");
                value = new String(vb, 0, 0, vb.length);
            }
            buffer.append(value);
            buffer.append("\r\n");
            dos.writeBytes(buffer.toString());
            OsgiAttributes.writeAttr(dos, e.getValue(), null);
        }
        dos.flush();
    }

}
