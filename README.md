# LogIt – A Smart Logging Library for Android Developers

LogIt is a lightweight logging library for Android that helps developers manage and view logs within their own application.
It provides a consistent logging API, local Room-based storage, and a built-in log viewer screen (LogViewerActivity) that can be easily launched from within your app.
Ideal for developers who want to inspect logs during development, testing, or internal QA, directly from the UI — without relying on Android Studio or Logcat

---

## Features

- ✅ Easy-to-use API: `DEBUG`, `INFO`, `WARNING`, `ERROR`
- ✅ Automatic log storage using Room (SQLite)
- ✅ Built-in log viewer screen (`LogViewerActivity`)
- ✅ Share or delete logs directly from the UI
- ✅ Supports level-based filtering
- ✅ Great for internal QA and debugging during development

---
## Getting Started
### Step 1 – Add the following to your settings.gradle.kts file:

```
    pluginManagement {
         repositories {
            // your existing repos
            maven("https://jitpack.io") // <-- add this here
         }
    }
```
### Step 2 – Add the code below to your settings.gradle.kts at the end of repositories:

```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url = uri("https://jitpack.io") }
		}
	}
```
### Step 3 – Add the library dependency

```
	dependencies {
	        implementation("com.github.avitalVissoky:Logit:1.0.1")
	}
```
### Step 4 – Sync gradle


---

## Usage

#### 1. Init the logger
```
LogMe.init(getApplicationContext());
```
#### 2. Start logging:
 ```
LogMe.debug("MainActivity", "This is a debug log");
LogMe.info("LoginActivity", "User successfully logged in");
LogMe.error("Network", "Failed to fetch data from server");
LogMe.log(LogLevel.WARNING, "API", "Deprecated endpoint called");

```
##  View logs in app
```
Intent intent = new Intent(context, LogViewerActivity.class);
context.startActivity(intent);
```

The viewer allows you to:
1. Filter logs by level (e.g. ERROR.. )
2. Share logs as plain text
3. Clear the log history

## Log Structure

Each log entry stored by the library contains the following fields:

| Field      | Type       | Description                                 |
|------------|------------|---------------------------------------------|
| `id`       | `int`      | Auto-generated unique ID                    |
| `timestamp`| `long`     | The time the log was created (Unix millis)  |
| `level`    | `LogLevel` | The severity of the log (e.g., DEBUG, INFO) |
| `tag`      | `String`   | The component or class where log originated |
| `message`  | `String`   | The actual log message                      |

##  Screenshots
<div>
<img src="https://github.com/user-attachments/assets/aac849f9-4985-4075-9143-1538b795c505"style="height:400px;"/>
<img src="https://github.com/user-attachments/assets/2f223ca0-a7f7-42dd-ab3d-00d35b584ff8"style="height:400px;"/>
<img src="https://github.com/user-attachments/assets/94fbe4cd-819c-4f50-8e60-6ba38433a70f"style="height:400px;"/>
<img src="https://github.com/user-attachments/assets/b328fdb8-48b8-413c-a844-f0d7e4565239"style="height:400px;"/>
</div>

## License

This project is licensed under the MIT License.
