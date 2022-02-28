# music-management
a music management app for Android based on the LastFM API.

### This app is built upon considering following scenarios:

Main Screen shows saved(from database) album list.
Search Screen loads artist list matching user entered searchText. Tap on any artist will show Top Albums of selected artist.
Top Albums screen shows best albums of an artist.Tap on any album will show its detail information. 
Album Details screen will show detail information of album. Tapping on download will save album locally.


### Technology stack

#### Architecture
- MVVM Architecture
 
#### Android Architectural Components Used

- Coroutine - For async remote operations
- LiveData
- Flow
- Jetpack navigation
- Pure DI
- ViewModel
- DataBinding
- Room

#### Libraries Used
- Retrofit - For effective network operations
- coil - For loading album images
