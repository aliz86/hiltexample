Component structure, scopes, bindings

Hilt assumes you will not be using your own components, but instead will be using Hilt predefined components,
Same goes with scopes
https://developer.android.com/training/dependency-injection/hilt-android#component-hierarchy
Also, binding means what object you get for free with that component. For example with AppComponent you get the "Application" object.


Using Hilt:
Steps:
1. Go to your custom "Application" Class, and annotate it with @HiltAndroidApp . This way you declare that this class (this application class) is the one that you want to set up hilt.
2. you won't be injecting yourself, won't use this: component.inject(this) (Although you will use the same @Inject lateinit var ... like dagger) (So anywhere you use @Inject private lateintit var.. , you have to use @AndroidEntryPoint)
Instead, whenever you want to use the injection, you use @AndroidEntryPoint (in activity, Fragment ...) except for the Class "MyApplication", in "MyApplication" class, you will use @inject , ...

Note that if you annotate a fragment with "@AndroidEntryPoint", you must annotate its activity with @AndroidEntryPoint , too.

3. In the modules you have to add @InstallIn(ActivityComponent::class) (hilt.ActivityComponent) so Hilt knows into which component I want that module to install.
4. 
5. 
6. Flutter, iOS