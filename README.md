# drawMultipleLines
open source library to draw multiple lines with desired line color and size on canvas

# Installation

## Step 1. 
Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

````
allprojects {
repositories {
...
maven { url 'https://jitpack.io' }
  }
}

````


## Step 2.
Add the dependency

````
dependencies {
implementation 'com.github.AkmalAfzal07:drawMultipleLines:0.1.0'
}

````

# Usage

Add this in your your layout
````
<com.example.drawmultiplelines.DrawingLineView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:lineColor="#FF0000"
        app:lineWidth="10.0"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toTopOf="@+id/undo_btn"
        />
    <Button
        android:id="@+id/undo_btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="undo"
        android:layout_marginBottom="20dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/redo_btn"
        />
    <Button
        android:id="@+id/redo_btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="redo"
        app:layout_constraintTop_toTopOf="@+id/undo_btn"
        app:layout_constraintBottom_toBottomOf="@+id/undo_btn"
       app:layout_constraintStart_toEndOf="@+id/undo_btn"
        app:layout_constraintEnd_toEndOf="parent"
        />
````
