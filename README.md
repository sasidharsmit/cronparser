# cronparser
A java command line application that parses the input cron expression and prints the expanded times/days for each of the date/time fields, along with the command field.

## Usage

```
$ java -jar cronparser-1.0-SNAPSHOT.jar */15 0 1,15 * 1-5 /usr/bin/find
```

## Constraints

The output should be formatted as a table with the field name taking the first 14 columns and the times as a space-separated list following it. 

For example, the following input argument:
```
*/15 0 1,15 * 1-5 /usr/bin/find
```

Should yield the following output:
```
        minute 0 15 30 45 
          hour 0 
    dayOfMonth 1 15 
         month 1 2 3 4 5 6 7 8 9 10 11 12 
   day of week 1 2 3 4 5 
       command /usr/bin/find 
```

