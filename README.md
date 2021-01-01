# Swec
## State aWare Epoxy Controller

[![](https://jitpack.io/v/premacck/Swec.svg)](https://jitpack.io/#premacck/Swec)

An Adapter class build on top of [Epoxy](https://github.com/airbnb/epoxy/)'s [`Typed2EpoxyController`](https://github.com/airbnb/epoxy/blob/master/epoxy-adapter/src/main/java/com/airbnb/epoxy/Typed2EpoxyController.java), which handles the following states:
- `STATE_LOADING` for the initial, loading state
- `STATE_LOADING_MORE` in case of pagination, to show a loader after the list items
- `STATE_SUCCESS` the successful state
- `STATE_EMPTY` the empty state
- `STATE_ERROR` the error state

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
