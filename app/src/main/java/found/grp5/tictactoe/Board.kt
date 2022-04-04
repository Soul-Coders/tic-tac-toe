package found.grp5.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.Toast
import found.grp5.tictactoe.databinding.ActivityBoardBinding

class Board : AppCompatActivity(), View.OnClickListener {
    val EMPTY = '\u0000'
    private var xTurn = true
    private var toast: Toast? = null
    private var grid: Array<CharArray> = Array(3) { CharArray(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewbinding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

        viewbinding.button00.setOnClickListener(this)
        viewbinding.button01.setOnClickListener(this)
        viewbinding.button02.setOnClickListener(this)

        viewbinding.button10.setOnClickListener(this)
        viewbinding.button11.setOnClickListener(this)
        viewbinding.button12.setOnClickListener(this)

        viewbinding.button20.setOnClickListener(this)
        viewbinding.button21.setOnClickListener(this)
        viewbinding.button22.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        toast?.cancel()

        // get the actual button which was pressed
        val button: Button = findViewById(view!!.id)

        // check if the button was already occupied by "X" or "O"; if yes ignore the click
        if (button.text.isNotEmpty()) {
            return
        }

        // get the button name: format `button_xy`; where x,y is the coordinate of the button in the grid
        val name = button.resources.getResourceName(view.id)

        // `name` format: `found.grp5.tictactoe:id/button_xy`; where we want xy; i.e. the last two characters
        val pos = name.takeLast(2)

        // store the x, y as a pair of integer that is to be used when storing the input in the grid 2d array
        val (x, y) = Pair(pos.first().digitToInt(), pos.last().digitToInt())

        // Determine what is to be placed in the grid: "X" or "O"
        val placeVal = if (xTurn) "X" else "O"

        // charSequence to char 🥲
        grid[x][y] = placeVal.last()

        // set the button text as either "X" or "Y"
        button.text = placeVal
        // set the text size on the button
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 72f)

        // get a toast showing who has the next turn
        toast = Toast.makeText(this@Board, "Next turn: ${if (xTurn) "O" else "X"}", Toast.LENGTH_SHORT)

        // display the toast
        toast?.show()

        // negate the `xTurn` to get the next player
        xTurn = !xTurn
    }
}