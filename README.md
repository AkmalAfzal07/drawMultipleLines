# drawMultipleLines
A library to draw multiple lines with desired line color and size on canvas just move your finger a line will follow your finger path.
###### -> set line size type:Foat
###### -> set line color type:Color

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

Add ```` DrawingLineView ```` in your your layout 
```` lineColor ```` attribute to change color of line 
```` lineWidth ```` attribute to change size of line

````
<com.example.drawmultiplelines.DrawingLineView
        android:layout_width="match_parent"
         android:id="@+id/customeView"
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

#### Or from code like
````
private var customDrawing: DrawingLineView? = null 
customDrawing = findViewById(R.id.customeView) 
customDrawing?.setLineColor(resources.getColor(R.color.gray_paint))
customDrawing?.setLineWidth(40F)

````

#### To undo and redo lines

// To remove a line  
````
 findViewById<Button>(R.id.undo_btn).setOnClickListener { 
            
          customDrawing?.undo()
    }
    
 ````

// To retreive a removed line
 ````
 findViewById<Button>(R.id.redo_btn).setOnClickListener { 
            
          customDrawing?.redo()
    }
   
 ````
 
  ## Screen shots
  #### I use this in another project as i show below
 <img src="https://user-images.githubusercontent.com/72786046/172308242-79ecdaad-683c-4ec8-8383-35a89709abf3.jpeg" width="400" height="790">
 <img src="https://user-images.githubusercontent.com/72786046/172308442-14556b4a-d36c-4a9d-a795-ca733110acbf.jpeg" width="400" height="790">

## Good Luck !
