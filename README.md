# User Comments
A sample Android application is made in Kotlin to demonstrate the implementation of MVVM with Clean Architecture.

The application fetches Reviews List from the [GetYourGuideAPI](https://travelers-api.getyourguide.com/activities/23776/reviews) incrementally(Pagging) and stores them in a local database. Data is always displayed using the Single Source of Truth principle which is SQLite Database.

# Tested Items
 - ***App Database*** 
 - ***ReviewDaoTest*** - Includes Paging 3 Test

# Used Libraries
 - ***Dagger Hilt*** - Used for Dependency Injection.
 - ***Retrofit*** - Used to make easier Network API calls.
 - ***Coroutines*** - For managing long running or network tasks off the main thread.
 - ***GSON*** - A converter for JSON Serialization.
 - ***Glide*** - An image loading library made in Kotlin Coroutines.
 - ***LiveData*** - Objects which can be observed by UI about changes. 
 - ***ViewModel*** - Stores and manages UI related data in a lifecycle aware way.
 - ***Paging 3*** - Used to display data in list in Paginated form.
 - ***Room*** - Used for persistent storage in Android.
 - ***Navigation component*** -  Used for implement navigation.
 - ***Google Truth*** - Used for make easier and readable test assertions
