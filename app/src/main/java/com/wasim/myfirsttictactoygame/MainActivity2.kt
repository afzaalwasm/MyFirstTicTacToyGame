package com.wasim.myfirsttictactoygame

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity2 : AppCompatActivity() {
    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    private var totalCells = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    var player1WinsCounts = 0
    var player2WinsCounts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_main2
        )
    }

    fun buClick(view: View) {
        val buSelected = view as Button
        var cellId = 0
        when (buSelected.id) {
            R.id.bu1 -> cellId = 1
            R.id.bu2 -> cellId = 2
            R.id.bu3 -> cellId = 3
            R.id.bu4 -> cellId = 4
            R.id.bu5 -> cellId = 5
            R.id.bu6 -> cellId = 6
            R.id.bu7 -> cellId = 7
            R.id.bu8 -> cellId = 8
            R.id.bu9 -> cellId = 9
        }
        playGame(cellId, buSelected)
    }


    private fun playGame(cellId: Int, buSelected: Button) {
        if (activePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            totalCells.remove(cellId)
            activePlayer = 2
        } else {
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.darkGreen)
            player2.add(cellId)
            totalCells.remove(cellId)
            activePlayer = 1
        }
        buSelected.isEnabled = false
        checkWinner()
    }

    private fun resetTotalCells() {
        totalCells.clear()
        totalCells.addAll(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
        Log.d("TAG", "resetTotalCells: $totalCells")
    }

    private fun checkWinner() {
        val winnerList: ArrayList<List<Int>> = ArrayList()
        winnerList.add(listOf(1, 2, 3))
        winnerList.add(listOf(1, 4, 7))
        winnerList.add(listOf(2, 5, 8))
        winnerList.add(listOf(3, 6, 9))
        winnerList.add(listOf(4, 5, 6))
        winnerList.add(listOf(7, 8, 9))
        winnerList.add(listOf(1, 5, 9))
        winnerList.add(listOf(3, 5, 7))
        winnerList.forEach {
            when {
                player1.containsAll(it) -> {
                    player1WinsCounts++
                    Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_SHORT).show()
                    restartGame()
                    return@forEach
                }
                player2.containsAll(it) -> {
                    player2WinsCounts++
                    Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_SHORT).show()
                    restartGame()
                    return@forEach
                }
                (player1.size + player2.size) == 9 -> {
                    restartGame()
                    return@forEach
                }
                activePlayer == 2 -> {
                    autoPlay()
                }
            }
        }
    }

    private fun autoPlay() {
        if (totalCells.isEmpty()) {
            restartGame()
            return
        }
        val r = Random()
        val randIndex = r.nextInt(totalCells.size)
        val cellId = totalCells[randIndex]
        val buSelected: Button = when (cellId) {
            1 -> bu1
            2 -> bu2
            3 -> bu3
            4 -> bu4
            5 -> bu5
            6 -> bu6
            7 -> bu7
            8 -> bu8
            9 -> bu9
            else -> {
                bu1
            }
        }
        playGame(cellId, buSelected)
    }
    private fun restartGame() {
        activePlayer = 1
        player1.clear()
        player2.clear()
        resetTotalCells()
        (1..9).forEach { cellId ->
            val buSelected: Button? = when (cellId) {
                1 -> bu1
                2 -> bu2
                3 -> bu3
                4 -> bu4
                5 -> bu5
                6 -> bu6
                7 -> bu7
                8 -> bu8
                9 -> bu9
                else -> {
                    bu1
                }
            }
            buSelected!!.text = ""
            buSelected.setBackgroundResource(R.color.whileBu)
            buSelected.isEnabled = true
        }
      textView1.setOnClickListener{
          "Player1: $player1WinsCounts, Player2: $player2WinsCounts"
      }


        Toast.makeText(
            this,
            "Player1: $player1WinsCounts, Player2: $player2WinsCounts",
            Toast.LENGTH_LONG
        ).show()
    }
}