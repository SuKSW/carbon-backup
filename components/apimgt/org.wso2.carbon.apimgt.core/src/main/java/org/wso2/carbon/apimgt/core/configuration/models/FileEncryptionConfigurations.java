/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.apimgt.core.configuration.models;

import org.wso2.carbon.kernel.annotations.Configuration;
import org.wso2.carbon.kernel.annotations.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold File Encryption configuration parameters
 */
@Configuration(description = "File Encryption Configurations")
public class FileEncryptionConfigurations {

    @Element(description = "enable file encryption")
    private boolean enabled = false;
    @Element(description = "files to encrypt")
    private List<String> filesToEncrypt = new ArrayList<>();

    public FileEncryptionConfigurations() {
        this.filesToEncrypt.add("NameOfAFile");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getFilesToEncrypt() {
        return filesToEncrypt;
    }

    public void setFilesToEncrypt(List<String> filesToEncrypt) {
        this.filesToEncrypt = filesToEncrypt;
    }
}
