JMP ClassLoader Default Task

Create console application for dynamic reloading of existing development functionality. Existing funcionality should be placed in specifed directory and should be archived in jar. The application should have console menu for choosing option, the output should be done through usage of log4j library. 

Additional notes to task:

Lets assume your console app is doing reporting.
- Report 1 impl is in one jar
- Report 2 impl is in another jar
- Report 3 impl is in another jar

In console app you can chose: report1, report2, report3. Depending on the chosen report the appropriate imp will do report.

Reports can be implemented as classes 

interface Report {

   void report();

}
