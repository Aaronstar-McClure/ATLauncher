/**
 * Copyright 2013 by ATLauncher and Contributors
 *
 * ATLauncher is licensed under CC BY-NC-ND 3.0 which allows others you to
 * share this software with others as long as you credit us by linking to our
 * website at http://www.atlauncher.com. You also cannot modify the application
 * in any way or make commercial use of this software.
 *
 * Link to license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
package com.atlauncher.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.atlauncher.App;

public class Language {

    private String name;
    private String localizedName;
    private File file;
    private Properties properties;
    private Properties english;

    public Language(String name, String localizedName) {
        this.name = name;
        this.localizedName = localizedName;
        this.file = new File(App.settings.getLanguagesDir(), name.toLowerCase() + ".lang");
        properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            App.settings.getConsole().logStackTrace(e);
        } catch (IOException e) {
            App.settings.getConsole().logStackTrace(e);
        }
        loadEnglishBackup();
    }
    
    private void loadEnglishBackup() {
        english = new Properties();
        try {
            english.load(new FileInputStream(new File(App.settings.getLanguagesDir(), "english.lang")));
        } catch (FileNotFoundException e) {
            App.settings.getConsole().logStackTrace(e);
        } catch (IOException e) {
            App.settings.getConsole().logStackTrace(e);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getLocalizedName() {
        return this.localizedName;
    }

    public File getFile() {
        return file;
    }

    public String getString(String property) {
        if (properties.containsKey(property)) {
            return properties.getProperty(property);
        }else{
            return english.getProperty(property, "Unknown Property: " + property);
        }
    }

    public String toString() {
        return this.localizedName;
    }
}
