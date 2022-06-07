package com.example.drawmultiplelines

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs


class DrawingLineView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var lineColor: Int = 0
    private var lineWidth: Float = 6F

    private lateinit var mCanvas: Canvas
    private lateinit var mPath: Path
    private lateinit var mPaint: Paint
    private lateinit var circlePaint: Paint
    private lateinit var outercirclePaint: Paint
    private val undonePaths: ArrayList<Path> = ArrayList()
    private val paths: ArrayList<Path> = ArrayList()
    private var mX = 0f
    private var mY = 0f
    private val touchTolerance = 0f
    private var colorsMap: HashMap<Path, Int> = HashMap()
    private var sizeMap: HashMap<Path, Float> = HashMap()


    init {
        setUpAttributes(attrs)
        initializePaint()
    }

    override fun onDraw(canvas: Canvas) {

        for (p in paths.indices) {
            if (p < paths.size-1) {
                mPaint.color = colorsMap[paths[p+1]]!!
                mPaint.strokeWidth = sizeMap[paths[p+1]]!!
            }
            else
            {
                mPaint.color = colorsMap[paths[p]]!!
                mPaint.strokeWidth = sizeMap[paths[p]]!!
            }
            canvas.drawPath(paths[p], mPaint)
        }
//        mPaint.color = lineColor
//        canvas.drawPath(mPath, mPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // if (x <= cx+circleRadius+5 && x>= cx-circleRadius-5) {
                // if (y<= cy+circleRadius+5 && cy>= cy-circleRadius-5){
                // paths.clear();
                // return true;
                // }
                // }
                Log.e("TAG", "ACTION_DOWN: ")
                touchStart(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e("TAG", "ACTION_MOVE: ")
                touchMove(x, y)
                return true
            }
            MotionEvent.ACTION_UP -> {
                Log.e("TAG", "ACTION_UP: ")
                touchUp()
                return true
            }
        }
        return false
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.AkmalDrawing, 0, 0)
        try {
            lineColor = typedArray.getColor(R.styleable.AkmalDrawing_lineColor, Color.BLACK)
            lineWidth = typedArray.getFloat(R.styleable.AkmalDrawing_lineWidth, 6F)
        } finally {
            typedArray.recycle()
        }
    }

    private fun initializePaint() {
        circlePaint = Paint()
        mPaint = Paint()
        outercirclePaint = Paint()
        outercirclePaint.isAntiAlias = true
        circlePaint.isAntiAlias = true
        mPaint.isAntiAlias = true
        mPaint.color = lineColor
        outercirclePaint.color = resources.getColor(com.google.android.material.R.color.design_default_color_error)
        circlePaint.color = resources.getColor(com.google.android.material.R.color.abc_decor_view_status_guard)
        outercirclePaint.style = Paint.Style.STROKE
        circlePaint.style = Paint.Style.FILL
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = lineWidth
        outercirclePaint.strokeWidth = 6F
        mCanvas = Canvas()
        mPath = Path()
        paths.add(mPath)
        colorsMap[mPath] = lineColor
        sizeMap[mPath] = lineWidth
//        undonePaths.add(mPath)
    }

    fun setLineColor(color: Int) {
        lineColor = color
//        mPaint = Paint()
//        mPaint.color = lineColor
        invalidate()
        requestLayout()
    }

    fun getLineColor(): Int {
        return lineColor
    }

    fun setLineWidth(width: Float) {
        lineWidth = width
//        mPaint.strokeWidth= lineWidth
        invalidate()
        requestLayout()
    }

    fun getLineWidth(): Float {
        return lineWidth
    }


    private fun touchStart(x: Float, y: Float) {
        mPath.reset()
        mPath.moveTo(x, y)
        mX = x
        mY = y
        invalidate()
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = abs(x - mX)
        val dy = abs(y - mY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
        }
        invalidate()
    }

    private fun touchUp() {
        mPath.lineTo(mX, mY)
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint)
        // kill this so we don't double
        mPath = Path()
        colorsMap[mPath] = lineColor
        sizeMap[mPath] = lineWidth
        paths.add(mPath)
        invalidate()
    }

    fun undo() {

        if (paths.size > 1) {
            undonePaths.add(paths[paths.size-2])
            paths.removeAt(paths.size - 2)
            Log.e("TAG", "onCreate: undo ")
            invalidate()
        }

        if (paths.size ==0 ) {
            mPath = Path()
            paths.add(mPath)
        }
    }

    fun redo() {
        if (undonePaths.size > 0) {
            Log.e("TAG", "onCreate: redo ")
            paths.add(undonePaths.removeAt(undonePaths.size - 1))
            invalidate()
        }
    }
}