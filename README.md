# D4J-Commands
A lightweight framework for creating commands with the Discord4J wrapper of the Discord API with ease.

## Features
 * Easily and effectively create commands.
 * Sort commands into classes.
 * Built in permission checking with custom and Discord permissions.
 
## Setup
D4J-Commands is available in my maven repository; add this as a dependency as so:
```
<repositories>
    <repository>
        <id>enderaura-repo</id>
        <url>http://repo.enderaura.me/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.enderaura</groupId>
        <artifactId>d4j-commands</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
_NB: You will have to make sure that this is included in the jar, or make these files available at runtime in another way._

Once you have added the dependencies, you will need to initialise the framework in your code. The first step to this is creating the `CommandManager` which will be used to register and manage commands.

This can be done as so:
```
private static IDiscordClient client;
private static CommandManager commandManager;

public static void main(String[] args){
    // Code to build IDiscordClient (but not login) and 
    // set the field 'client' to it.
    
    commandManager = new CommandManager(client);
    
    client.login();
}
```
_It may be useful to create a getter for the private fields, and also take an approach outside of the main method when loading._

You are now ready to begin using the framework.

## Usage
A command can be created by simply making a class that extends `Command` and implements the required method (`public void onCommand(IMessage message, IUser sender, List<String> args)`) and has a constructor matching super (`public Command(Permission permission)`).

```java
import me.enderaura.commands.Command;

public class TestCommand extends Command{
    
    public Command(Permission permission){
        super(permission);
    }
    
    public void onCommand(IMessage message, IUser sender, List<String> args){
        
    }
    
}
```

Add command functionality inside the `onCommand` method. Accessing all of the Discord4J library is safe from here.