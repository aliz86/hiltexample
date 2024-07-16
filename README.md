Component structure, scopes, bindings

Hilt assumes you will not be using your own components, but instead will be using Hilt predefined components.
So you will define modules and Hilt has already defined the components.
Same goes with scopes
https://developer.android.com/training/dependency-injection/hilt-android#component-hierarchy
Also, binding means what object you get for free with that component. For example with AppComponent you get the "Application" object.

Using Hilt:
Steps:
1. Go to your custom "Application" Class, and annotate it with @HiltAndroidApp . This way you declare that this class (this application class) is the one that you want to set up hilt.
2. you won't be injecting yourself, won't use this: component.inject(this) (Although you will use the same @Inject lateinit var ... like dagger) (So anywhere you use @Inject private var.. , you have to use @AndroidEntryPoint)
Instead, whenever you want to use the injection, you use @AndroidEntryPoint (in activity, Fragment ...) except for the Class "MyApplication", in "MyApplication" class, you will use @inject , ...

Note that if you annotate a fragment with "@AndroidEntryPoint", you must annotate its activity with @AndroidEntryPoint , too.

-- So, by far, you can use inject service in both application and other classes (in application via @HiltAndroidApp and other classes via @AndroidEntryPoint)
3. In the modules you have to add @InstallIn(ActivityComponent::class) (hilt.ActivityComponent) so Hilt knows into which component I want that module to install. (refer to below Hilt component	Injector for)
4. If you need scoping, Hilt already has pre-defined scopes: https://developer.android.com/training/dependency-injection/hilt-android#component-scopes
You can also rename these scopes using e.g., AliasOf(Singleton::class)
5. 
6. Flutter, iOS

------------------------------------------------------
https://developer.android.com/training/dependency-injection/hilt-android#component-default
This shows that if I have a module with for eaxmple, @InstallIn(ActivityComponent::class), I can get the activity instance (and even xast it to AppCompatActivity or ComponentActivity depending on my application) inside the module. refer to this project class: ActivityModule

Hilt component	Injector for

SingletonComponent	Application

ActivityRetainedComponent	N/A

ViewModelComponent	ViewModel

ActivityComponent	Activity

FragmentComponent	Fragment

ViewComponent	View

ViewWithFragmentComponent	View annotated with @WithFragmentBindings

ServiceComponent	Service

Note: Hilt doesn't generate a component for broadcast receivers because Hilt injects broadcast receivers directly from SingletonComponent.
-------------------------------------------------------

Hilt and ViewModels:
1- first of all for using Hilt with viewmodel, add these dependencoes to app build.gradle:
implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.2.0")
kapt("androidx.hilt:hilt-compiler:1.2.0")

2- add @HiltViewModel above the viewmodel class

3- Then add module installin...:
@Module
@InstallIn(ViewModelComponent::class)
class MyDiModule

4- You annotate your Activity with @AndroidEntryPoint and use these lines like in this project:

private val ourViewModel: OurViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiltExampleTheme {
                ourViewModel.doSomething()



5- If you want to use SavedStateHandle: (see this project)

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hiltexample.model.UserRepository
import dagger.assisted.Assisted

import javax.inject.Inject


class OurViewModel @Inject constructor(private val userRepository: UserRepository,
@Assisted savedStateHandle: SavedStateHandle
)  : ViewModel() {


}

*Attention: SavedStateHandle

-------------------------------------

Hilt complete adding dependency guide:

To use Hilt in your Android project, including ViewModels, you need to add several dependencies to your Gradle files:

**1. Project-Level `build.gradle` (or `build.gradle.kts`):**

* Add the Hilt Android Gradle plugin in the `plugins` block of your project-level `build.gradle` (if you are using the older `groovy` based gradle file):

   ```groovy
   plugins {
       // ... other plugins

       id 'com.google.dagger.hilt.android' version '2.48' apply false // Use the latest version available
   }
   ```

* Or add it to your `build.gradle.kts` file (if you are using the newer `kotlin` based gradle files)

   ```kotlin
   plugins {
       // ... other plugins

       id("com.google.dagger.hilt.android") version "2.48" apply false // Use the latest version available
   }
   ```

* In the same file, make sure you have the Google Maven repository in your `buildscript` block:

   ```groovy
   buildscript {
       repositories {
           // ... other repositories
           google()  
       }
       // ...
   }
   ```

**2. Module-Level `build.gradle` (or `build.gradle.kts`):**

* Apply the Hilt plugin at the top of your **app module's** `build.gradle` (or `build.gradle.kts`) file:

   ```groovy
   apply plugin: 'com.android.application'
   apply plugin: 'kotlin-android' 
   apply plugin: 'kotlin-kapt' // If you're using kapt for annotation processing
   apply plugin: 'dagger.hilt.android.plugin'
   ```

* Add the necessary Hilt dependencies. In the same module-level Gradle file, add these to the `dependencies` block:

   ```groovy
   dependencies {
       implementation("com.google.dagger:hilt-android:2.48") // Use the latest version available
       kapt("com.google.dagger:hilt-android-compiler:2.48")  // Use the latest version available

       // ViewModel dependency
       implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
       kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")

       // ... other dependencies ...
   }
   ```

**3. AndroidManifest.xml:**

* Add the Hilt Android App component to your `AndroidManifest.xml` file within the `<application>` tag:

   ```xml
   <application
       ... 
       android:name="dagger.hilt.android.HiltAndroidApp"> 

       ... 
   </application>
   ```

**Important Notes:**

* **Version Consistency:** Ensure that the Hilt versions (e.g., `2.48`) used in your project-level and app-level `build.gradle` files are the same.
* **Synchronization:** After making these changes, synchronize your project with Gradle files (usually prompted automatically by Android Studio).
* **ViewModel Injection:** With the `hilt-lifecycle-viewmodel` dependency added, you can now inject dependencies into your ViewModels using `@HiltViewModel` and `@Inject`:

   ```kotlin
   @HiltViewModel
   class MyViewModel @Inject constructor(
       private val repository: MyRepository
   ) : ViewModel() { 
       // ...
   }
   ```

Once you've completed these steps, rebuild your project. Now you can start using Hilt for dependency injection, including injecting dependencies into your ViewModels.
