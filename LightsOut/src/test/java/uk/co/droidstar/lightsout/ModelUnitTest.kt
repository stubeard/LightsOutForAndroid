package uk.co.droidstar.lightsout

import org.junit.Test

import org.junit.Assert.*
import uk.co.droidstar.lightsout.ui.main.IModel
import uk.co.droidstar.lightsout.ui.main.MainViewModel
import uk.co.droidstar.lightsout.ui.main.maxColumns
import uk.co.droidstar.lightsout.ui.main.maxRows

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ModelUnitTest {
    @Test
    fun testMinXYBoundary()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( true,  true,  false, false, false ),
                                arrayOf( true,  false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ) )

        model.set( 0, 0 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testMaxXYBoundary()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, true  ),
                                arrayOf( false, false, false, true,  true  ) )

        model.set( 4, 4 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testMinXMaxYBoundary()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( true,  false, false, false, false ),
                                arrayOf( true,  true,  false, false, false ) )

        model.set( 0, 4 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testMaxXMinYBoundary()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, true,  true  ),
                                arrayOf( false, false, false, false, true  ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ) )

        model.set( 4, 0 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testOutOfRangeMinXYBoundary()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ) )

        model.set( -1, -1 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testOutOfRangeMaxXYBoundary()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ) )

        model.set( 5, 5 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testOutOfRangeMinXMaxYBoundary()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ) )

        model.set( -1, 5 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testOutOfRangeMaxXMinYBoundary()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, false, false, false ) )

        model.set( 5, -1 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testGoodValue1()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, false, false ),
                                arrayOf( false, false, true,  false, false ),
                                arrayOf( false, true,  true,  true,  false ),
                                arrayOf( false, false, true,  false, false ),
                                arrayOf( false, false, false, false, false ) )

        model.set( 2, 2 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    @Test
    fun testGoodValue2()
    {
        val model = createEmptyModel()

        val expected = arrayOf( arrayOf( false, false, false, false, false ),
                                arrayOf( false, true,  true,  false, false ),
                                arrayOf( true,  false, false, true,  false ),
                                arrayOf( false, true,  true,  false, false ),
                                arrayOf( false, false, false, false, false ) )

        model.set( 2, 2 )
        model.set( 1, 2 )

        val result = getResult( model )

        assertArrayEquals( expected, result )
    }

    private fun createEmptyModel() : IModel{
        val model : IModel = MainViewModel()
        model.reset()

        return model
    }

    private fun getResult( model : IModel ) : Array<BooleanArray>{
        val result = Array(maxRows) { BooleanArray(maxColumns) }

        for (i in 0 until maxRows) {
            for (j in 0 until maxColumns) {
                result[i][j] = model.get(i, j)
            }
        }
        return result
    }
}
