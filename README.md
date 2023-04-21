# CyberLingo

Kyle Jacoby

## Instructions for How to Download and Install CyberLingo

### Download Source Code and Import to IDE
1. Unzip source code from provided zip folder.
2. Import project folder to chosen IDE (I used IntelliJ but it should hopefully work in Eclipse as well)

### Download and Install Dependencies
1. You will need JavaFX, MySQL, and MySQLconnectorj.
2. To download and install JavaFX, follow these steps:
   1. Go to https://gluonhq.com/products/javafx/ and download the most recent version.
   2. Extract the zip folder to the desired destination.
      1. It can be extracted wherever you want, just remember where you extracted it for later.
3. To download and install MySQL and MySQLconnectorJ on **Windows**, follow these steps:
   1. If you do not already have MySQL installed:
      1. Go to https://dev.mysql.com/downloads/installer/ to download the latest version of MySQL installer.
      2. Run the installer and follow the instructions to install MySQL.
   2. If you do have MySQL installed, you should already have MySQL installer on your system.
   3. Run MySQL Installer. Click the “Add” button on the right side of the screen
   4. Open “MySQL connectors”
   5. Select the most recent version of Connector J, then click the green arrow to prepare it for installation. Then click Next.
   6. Click Execute, and Connector J should be installed in the same directory as your MySQL installation.
4. To download and install MySQL and MySQLconnectorJ on **Mac**, follow these steps:
   1. If you do not already have MySQL installed:
      1. Go to https://dev.mysql.com/downloads/mysql/ to download the latest version of MySQL community.
      2. Follow these steps to install. https://dev.mysql.com/doc/mysql-macos-excerpt/5.7/en/macos-installation-pkg.html 
   2. Once MySQL is installed, go to https://dev.mysql.com/downloads/connector/j/ to download the tar or zip archive for Connector J
   3. Extract the file to your desired location.
      1. Similar to JavaFX, anywhere works but remember it for later.

### Add Dependencies to IDE

Disclaimer: I have not tested these steps for Eclipse, only on IntelliJ

For IntelliJ:

1. In the project that you imported earlier, go to File > Project Structure
2. Navigate to the “Libraries” tab
3. Click the + sign to add a library, then navigate to the location where you extracted the JavaFX folder.
   1. Navigate through the folder, finding and selecting the “lib” folder within, then click OK.
4. Select “Modules” and navigate to the “Dependencies” tab. 
5. Click the + and select “JARs and Directories”
   1. Navigate to the location where MySQL Connector J was installed.
      1. If installed through MySQL Installer, it will be in the same directory as MySQL (likely Program Files)
   2. Navigate through the folder, selecting the .jar file and clicking OK.
6. Click OK to apply these changes.
7. Next, go to Run > Edit Configurations
   1. Add a new configuration.
   2. Click “Modify Options”, then select “Add VM Options” from the list
   3. In the newly shown VM Options field, enter the following:
      1. --module-path "<absolute path to your JavaFX lib folder>" --add-modules=javafx.controls,javafx.fxml

For Eclipse:
1. Follow the steps in this article to add JavaFX to an Eclipse project. https://pragmaticways.com/how-to-add-javafx-to-eclipse-the-easy-way/
2. Follow the steps in this article to add the MySQL Connector J jdbc driver to Eclipse. https://javahelps.com/how-to-add-mysql-jdbc-driver-to-eclipse

### Running the Program
1. Run the Launcher class to run the CyberLingo program.
2. If a JavaFX error occurs, make sure the VM options from previous steps are added correctly.

