NOTE :- My project is based upon CRUD OPERATIONS performed on jsp+servlet+mysql+jdbc.(No Maven (or) Gradle (or) Spring Framework is used..in my project)

1.) In my server.java code,Add your "MYSQLDB" in this JDBC connector to MYSQL i.e..----> "jdbc:mysql://localhost:3306/mydb1",(here mydb1 is my database in case yours add your MYSQLDB name)

2.)Also in  : String username = "user02";                           ---> Add your MYSQL DB username

3.)Also in  : String Password = System.getenv("MYSQL_PASSWORD");    ---> In this string instead of  "MYSQL_PASSWORD" you have to place the value of password of your "MYSQL" as "DYNAMICALLY" i.e first up all u have to set ur "MYSQL" password to an variable in the "ENVIRONMENT VARIABLES"
STEPS:-
On Windows:
Open Environment Variables Dialog:

1.) Right-click on "This PC" or "Computer" (depending on your Windows version) and select "Properties".
Click on "Advanced system settings" on the left side.
In the System Properties window, click on the "Environment Variables..." button.
Add New System Variable:

2.) In the Environment Variables window (either for the current user or system-wide), under "System variables" or "User variables", click on "New...".
Enter MYSQL_PASSWORD as the variable name.
Enter your MySQL password as the variable value.
Click OK to save the variable.
Confirm Changes:

3.) Click OK in all open windows to confirm and apply the changes.
Restart any applications (like IDEs) that might need to pick up the new environment variable.

set YOUR_PASSWORD="your_mysql_password".

4.) Finally pass the YOUR_PASSWORD as an parameter of String Password = System.getenv("YOUR_PASSWORD");

5.)Save the changes & run the code in the webBrowser


