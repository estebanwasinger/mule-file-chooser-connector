/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.filechooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.mule.api.annotations.ConnectionStrategy;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.display.Path;
import org.mule.modules.filechooser.strategy.ConnectorConnectionStrategy;

/**
 * Anypoint Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="file-chooser", friendlyName="File Chooser")
public class FileChooserConnector {
    
    @ConnectionStrategy
    ConnectorConnectionStrategy connectionStrategy;

    @Processor
    public File openFile(@Path String filePath) throws IOException {
    	File file = getFile(filePath);
        return file;
    }

	private File getFile(String filePath) throws IOException {
		File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File " + file.getAbsolutePath() + " does not exist!");
        }
		return file;
	}
    
	@Processor(friendlyName="Open File as InputStream")
    public InputStream openFileAsIS(@Path String filePath) throws FileNotFoundException, IOException{
    	return new FileInputStream(getFile(filePath));
    }

    public ConnectorConnectionStrategy getConnectionStrategy() {
        return connectionStrategy;
    }

    public void setConnectionStrategy(ConnectorConnectionStrategy connectionStrategy) {
        this.connectionStrategy = connectionStrategy;
    }

}