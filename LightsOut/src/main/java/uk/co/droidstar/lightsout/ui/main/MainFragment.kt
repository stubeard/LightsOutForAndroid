package uk.co.droidstar.lightsout.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.main_fragment.*
import uk.co.droidstar.lightsout.MainActivity
import uk.co.droidstar.lightsout.R


class MainFragment : IView, Fragment() {

    private var buttons = Array(maxRows){ Array<Button?>(maxColumns){ null } }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: IModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        buttons = arrayOf( arrayOf( button11, button12, button13, button14, button15 ),
                           arrayOf( button21, button22, button23, button24, button25 ),
                           arrayOf( button31, button32, button33, button34, button35 ),
                           arrayOf( button41, button42, button43, button44, button45 ),
                           arrayOf( button51, button52, button53, button54, button55 ) )

        viewModel.addView( this )

        for( i in 0 until maxRows )
        {
            for( j in 0 until maxColumns ) {
                buttons[i][j]?.setOnClickListener {
                    viewModel.set(j, i)
                }
                buttons[i][j]?.setOnLongClickListener{
                    toggleActionBar()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.removeView( this)
    }

    override fun update() {
        for (i in 0 until maxRows) {
            for (j in 0 until maxColumns) {
                when (viewModel.get(i, j)) {
                    true -> buttons[i][j]?.setBackgroundColor(Color.parseColor("#00FF00"))
                    false -> buttons[i][j]?.setBackgroundColor(Color.parseColor("#004000"))
                }
            }
        }
    }

    private fun toggleActionBar() : Boolean
    {
        if( MainActivity.bar!!.isShowing)
        {
            MainActivity.bar?.hide()
        }
        else
        {
            MainActivity.bar?.show()
        }
        return true
    }
}

interface IView
{
    fun update()
}