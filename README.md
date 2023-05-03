# yuno-antifraud-signifyd-android

The YunoSignifyd library is a tool that allows integration with Signifyd's fraud detection service. 
This library uses the ThreatMetrix library to generate a device profile and send it to Signifyd's server for fraud analysis.

To integrate this library into your application, follow these steps:

##Step 1.

Add the YunoSignifyd library dependency to your app module's build.gradle file. Make sure to add the latest available version:

```Gradle
dependencies {
    implementation 'com.yuno.fraud-signifyd:yunosignifyd:0.1.0'
}
```

##Step 2.

Initialize the YunoSignifyd library in your application. To do this, call the initYunoSignifyd function in the onCreate method of your 
Application class, passing the organization identifier provided by Signifyd and the URL of Signifyd's fraud detection server as parameters:

```kotlin
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initYunoSignifyd("organization_identifier", "server_url")
    }
}
```
##Step 3.

Call the onCreateYunoSignifyd function in the onCreate method of your main activity, passing the application context as a parameter:

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize YunoSignifyd
        applicationContext.onCreateYunoSignifyd(this)
    }
}
```

##Step 4.

Call the onResumeYunoSignifyd function in the onResume method of your main activity to generate and send a device profile to Signifyd:

```kotlin
override fun onResume() {
    super.onResume()

    // Generate device profile and send to Signifyd
    applicationContext.onResumeYunoSignifyd { sessionId ->
        // Handle generated session ID
        Log.d(TAG, "Generated session ID: $sessionId")
    }
}
```
With this, your application should be ready to integrate with Signifyd's fraud detection service. Remember that you need to have an organization 
identifier and the URL of the fraud detection server provided by Signifyd to use this library effectively.


