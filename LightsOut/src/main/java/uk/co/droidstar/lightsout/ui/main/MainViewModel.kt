package uk.co.droidstar.lightsout.ui.main

import androidx.lifecycle.ViewModel

const val maxRows    = 5
const val maxColumns = 5

class MainViewModel : IModel, ViewModel() {
    private val offsets = arrayOf(
        arrayOf(0, 0),
        arrayOf(-1, 0),
        arrayOf(0, -1),
        arrayOf(1, 0),
        arrayOf(0, 1)
    )

    private var lights = Array(maxRows) { BooleanArray(maxColumns) }

    override val views = arrayListOf<IView>()

    init {
        randomise()
    }

    override fun set(x: Int, y: Int) {
        if ((x >= 0) &&
            (y >= 0) &&
            (x < maxColumns) &&
            (y < maxRows)
        ) {
            for (offset in offsets) {
                val xx = x + offset[0]
                val yy = y + offset[1]

                if ((xx >= 0) &&
                    (yy >= 0) &&
                    (xx < maxColumns) &&
                    (yy < maxRows)
                ) {
                    lights[xx][yy] = !lights[xx][yy]
                }
            }
        }
        fireModelChanged()
    }

    override fun get(x: Int, y: Int): Boolean {
        if ((x >= 0) &&
            (y >= 0) &&
            (x < maxColumns) &&
            (y < maxRows)
        ) {
            return lights[y][x]
        }
        return false
    }

    @Suppress("unused")
    override fun reset() {
        for (i in 0 until maxRows) {
            for (j in 0 until maxColumns) {
                lights[i][j] = false
            }
        }
    }

    override fun randomise() {
        for (i in 0 until maxRows) {
            for (j in 0 until maxColumns) {
                lights[i][j] = (Math.random() > 0.5)
            }
        }
    }

}

interface IModel
{
    val  views : ArrayList<IView>

    fun addView( view : IView )
    {
        if( ! views.contains( view ) )
        {
            views.add( view )
        }
        fireModelChanged()
    }


    fun removeView(view : IView )
    {
        if( views.contains( view ) )
        {
            views.remove( view )
        }
    }

    fun fireModelChanged()
    {
        for( view in views )
        {
            view.update()
        }
    }

    fun get( x : Int, y : Int ) : Boolean
    fun set( x : Int, y : Int )
    fun reset()
    fun randomise()

}
