package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recyclerview_movies.*
import kotlinx.android.synthetic.main.item_note.*

//Networks
//DEADLINE: Wednesday: 6PM.
//TODO: Before modifying my code please merge it into yours or create a branch from it.  //@NardineYousry.
//TODO: Create MovieRepository class                                                     //Done.
//TODO: try binding data to the item_note class without the images.                     //Done.
//TODO: find the way to pass images, first in a simple test then to the adapter.       //Not Done.
//TODO: for simple tests try binding data directly to the item_note layout.           //Not Done.
//TODO: after successful simple tests try linking data to the adapter.               //Done for MovieDetails.
//TODO: create adapter class.                                                       //Done.
//TODO: Link the recyclerView adapter to the network.                              //Done for MovieDetails.
//TODO: Testing, don't use pixel 2 in testing it causes errors!                   //Checked.
//Successful Test? -> Congratulations!
//Design
//TODO: item_note class might need modifications.                    //Depending on network results.
//TODO: plus point Movie Details Layout and the required code.      //if finished before deadline with 12 hours.

//DO NOT FORGET!
//TODO: I modified android:hint to tools:hint in item_note for testing purposes. //Modify in the end.
//TODO: I modified the manifest for testing purposes.              //Modify at end of tests.
//TODO: Remember to return to original state.                     //Complete!

//Errors i am facing right now:
//Errors i am facing right now:
//Errors i am facing right now:
//TODO:ReadBelow @NardineYousry.
//setContentView must be set to setContentView(R.layout.activity_main).
//bs lama b3ml kda program by3ml crash.
//lesa msh 3arf ezay a3ml mwdo3 al image bs malak 3mlalo documentation fel API interface.
//lesa msh 3arf ezay 7b3t lel adapter list ykon feha 7agat imageDetails / movieDetails f nfs al w2t.
//bs mthy2ly mmkn n3ml list gowa movieDetails ykon feha imageDetails.


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: READ BELOW! @NardineYousry.
        //error in the code above mfrood tkon R.layout.main_activity bs lama b3ml kda bideny error.
        //8albn error 3shan al code al network al mktob t7t mfrood ykon mktob f activity tanya.
        //73ml testbranch tany awreeky feh shakl al run al 3ady mfrood ykon 3amel ezay.

        //TODO: DO NOT DELETE THE CODE BELOW, IT IS COMMENTED FOR TEST PURPOSES.
        //switches you to the sign up layout
        textView_signUp.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
        //opens the activity that shows you the APP
        button_Preview.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerViewMovies::class.java)
            startActivity(intent)
        }
    }
}
        //Write your network code here
        //Write your network code here
        //Write your network code here




