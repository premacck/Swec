# Swec
## State aWare Epoxy Controller

[![](https://jitpack.io/v/premacck/Swec.svg)](https://jitpack.io/#premacck/Swec)

[![](https://androidweekly.net/issues/issue-447/badge)](https://androidweekly.net/issues/issue-447)


An Adapter class build on top of [Epoxy](https://github.com/airbnb/epoxy/)'s [`Typed2EpoxyController`](https://github.com/airbnb/epoxy/blob/master/epoxy-adapter/src/main/java/com/airbnb/epoxy/Typed2EpoxyController.java), which handles the following states:
- `STATE_LOADING` for the initial, loading state
- `STATE_LOADING_MORE` in case of pagination, to show a loader after the list items
- `STATE_SUCCESS` the successful state
- `STATE_EMPTY` the empty state
- `STATE_ERROR` the error state

This was done to keep things simple between developers, so instead of handling different states with different conditions in lifecycle classes, we decided to handle it in controller classes, with dedicated callback functions, to improve readability & maintainence.

## Integration

### Gradle

- Add the following in your root `build.gradle` at the end of repositories:
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

- In your module-level `build.gradle`:
```gradle
implementation 'com.github.premacck:Swec:1.0.0'
```


### Maven

- Add the JitPack repository to your build file:
```XML
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

- Add the dependency
```XML
<dependency>
  <groupId>com.github.premacck</groupId>
  <artifactId>Swec</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Usage

- Create a controller class extending `StateAwareEpoxyController` or `StateAwareEpoxyControllerList` (for a List<Item>).
```kotlin
  class MyStateAwareController : StateAwareEpoxyControllerList<Item>() {

  init {
    // Use this to have the `onLoadingState()` called initially
    setLoadingState()
  }

  override fun onLoadingState() {
    // Add loader/shimmer item here
    loaderItem { id("Loader item for $this") }
  }

  override fun onLoadingMoreState(data: List<Item>) {
    // This will only be called when data is not null
    loadData(data)
    loadMoreItem { id("Load More Item for ${this@SwecController}") }
  }

  override fun onSuccessState(data: List<T>?) {
    loadData(data)
  }

  private fun loadData(data: List<T>?) {
    // Load your data from `data`, as you would do normally in an `EpoxyController`
  }

  override fun onErrorOrEmptyState(data: List<T>?) {
    // Handle combined error/empty states
  }
}
```

- By default, `STATE_EMPTY` & `STATE_ERROR` will both invoke `onErrorOrEmptyState(data: List<T>?)`
- If you want to handle Error and Empty states differently, then pass `sameErrorAndEmptyStates = false` to the constructor of parent class
```kotlin
class MyStateAwareController : StateAwareEpoxyControllerList<Item>(sameErrorAndEmptyStates = false) {

  // Other implementations

  override fun onErrorState() {
    // Handle Error state
  }

  override fun onEmptyState() {
    // Handle Empty state
  }
}
```

- In your UI class:
  - The `setData(data)` function takes care of setting the states for you, according to the following conditions:
    * If the object or List is `null`, `LoadState` will be `STATE_ERROR`
    * If the object is a list and the List is empty, the `LoadState` will be `STATE_EMPTY`
    * If the object or List is not null or empty, `LoadState` will be `STATE_SUCCESS`
  - But of course, you can go ahead and specify your `LoadState` explicitly as well.
    
- UI class implementation
```kotlin
class ListActivity : AppCompatActivity() {

  // ...
  private val controller by lazy { MyStateAwareController() }
  
  //...
  
  private fun initRecyclerView() {
    epoxyyRecyclerView.setController(controller)
  }
  
  private fun fetchData() {
    someNetworkCall { response ->
      // Assuming response.data is a list of items
      setData(response.data)
    }
  }
  
  private fun setData(list: List<Items>?) {
    // This will handle the STATE_SUCCESS, STATE_ERROR & STATE_EMPTY automatically according to the logic explained above
    controller.setData(list)

    // You can also set state with data
    // controller.setData(list, STATE_SUCCESS)
  }
  
  private fun handleError() {
    controller.setData(null)

    // You can also set state with data
    // controller.setData(null, STATE_ERROR)
  }
  
  private fun handleEmpty() {
    controller.setData(emptyList())

    // You can also set state with data
    // controller.setData(null, STATE_EMPTY)
  }
}
```

### Sample

The sample for this library uses [Swapi SDK Revived](https://github.com/premacck/Swapi-SDK-revived) for quick & custom implementation of lists
