<h1 align="center">Blockbuster</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a> 
  <a href="https://github.com/ManaswiniKundeti/Blockbuster"><img alt="Build Status" src="https://github.com/ManaswiniKundeti/Blockbuster/workflows/Android%20CI/badge.svg"/></a> 
</p>

<p align="center">  
Sample android application to rent popular movies built on <b>Kotlin</b> using <b>Android Architecture components (ViewModel, LiveData, Room)</b>, <b>Retrofit</b> and <b>Coil</b> and <b>Coroutines</b>.
The app also uses <b>JetPack Navigation Component</b> to navigate among fragments and <b>Hilt</b> for dependency injection<br/>
The app also uses <b>Konfetti</b> to celebrate when movies are added to the cart🔥
</p>
</br>

<p align="center">
<img src="/previews/app_start_no_internet.jpeg" width=300/>
<img src = "/previews/movies_list_portrait.jpeg" width=300 />
<img src = "/previews/movie_details_portrait.jpeg" width=300 />
<img src = "/previews/empty_cart.jpeg" width=300 />
<img src = "/previews/added_to_cart.jpeg" width=300 />
<img src = "/previews/cart_portrait.jpeg" width=300 />
<img src = "/previews/movies_list_landscape.jpeg" width=400 />
<img src = "/previews/movie_details_landscape.jpeg" width=400 />
</p>

## Download
Go to the [Releases](https://github.com/ManaswiniKundeti/Blockbuster/releases) to download the lastest APK.

<img src="/previews/Blockbuster.gif" align="right" width="32%"/>

## App Features
- Movies Collection: Users can view all the movies that are available for rent on the home page.
- Movie Details: On clicking on the movie, users can view the movie details in a separate page.
- Add to cart: On viewing the details, the users can add the movie to their cart.
- View cart: Users can view their shopping cart items by clicking on the cart menu icon on the toolbar.

## Open-source libraries
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/)
- Coroutines
- JetPack
  - LiveData
  - ViewModel
  - Room
  - Naviagtion Component
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
  - Repository pattern
- [Retrofit2](https://github.com/square/retrofit)
- [Moshi](https://github.com/square/moshi/)
- [Coil](https://github.com/coil-kt/coil)
- [Hilt](https://dagger.dev/hilt/)
- [Konfetti](https://github.com/DanielMartinus/Konfetti)

## Architecture
Blockbuster is based on MVVM architecture and a repository pattern.

<img src=https://developer.android.com/topic/libraries/architecture/images/final-architecture.png width=500>

## API

Blockbuster uses the [The Movie Database Api](https://developers.themoviedb.org/3/getting-started/introduction)

## Find this repository useful?
Support it by joining __[stargazers](https://github.com/ManaswiniKundeti/Blockbuster/stargazers)__ for this repository.<br>

# License
```xml
Designed and developed by 2020 ManaswiniKundeti (Manaswini Kundeti)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
