# filehider25

#File Hider Application

Description:
Developed a Java-based file management application that allows users to securely hide and unhide files. The application uses email authentication for user validation and leverages a MySQL database to store file metadata and binary data. This project ensures file confidentiality by temporarily removing files from the file system and storing them in a hidden state.

Key Features:
- File Hiding: Users can hide files by storing their contents as binary data in a database, removing them from the file system. This operation involves reading file contents and using a prepared SQL statement to insert the data into a MySQL database.
- File Unhiding: Users can restore hidden files by retrieving the binary data from the database and writing it back to the file system. The application also deletes the record from the database once the file is successfully restored.
- Database Interaction: Utilizes JDBC for interacting with a MySQL database. Prepared statements are used for secure and efficient SQL operations, preventing SQL injection attacks.

Technologies Used:
- Java: Core programming language for application development.
- MySQL: Database management system for storing file metadata and binary data.
- JDBC: Java Database Connectivity for database interaction.
- File I/O Operations: Java classes such as `File`, `FileReader`, `FileWriter`, and `Clob` for file handling.
