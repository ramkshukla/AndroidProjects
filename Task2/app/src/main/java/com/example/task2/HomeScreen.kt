package com.example.task2


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeScreen() : Fragment(){
    lateinit var adapter: NewsAdapter
    lateinit var progressBar: ProgressBar
    lateinit var newsList: RecyclerView
    lateinit var retryBtn: Button
    var mcontext: Context? = null
    var articles = ArrayList<Articles>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mcontext = context

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        progressBar = view.findViewById(R.id.progressBarId)
        newsList = view.findViewById(R.id.newsList)
        retryBtn = view.findViewById(R.id.retryBtn)
        retryBtn.setOnClickListener {
            APICall()
        }
        APICall()
        swipe()
        return view
    }

    private fun swipe() {
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val newsAdapter = articles.get(viewHolder.adapterPosition)
//                var position = viewHolder.adapterPosition
                articles.removeAt(viewHolder.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(newsList)
//        new ItemTouchHelper (new ItemTouchHelper . SimpleCallback (0, ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(
//                @NonNull RecyclerView recyclerView,
//                @NonNull RecyclerView.ViewHolder viewHolder,
//                @NonNull RecyclerView.ViewHolder target
//            ) {
//                // this method is called
//                // when the item is moved.
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                // this method is called when we swipe our item to right direction.
//                // on below line we are getting the item at a particular position.
//                RecyclerData deletedCourse = recyclerDataArrayList . get (viewHolder.getAdapterPosition());
//
//                // below line is to get the position
//                // of the item at that position.
//                int position = viewHolder . getAdapterPosition ();
//
//                // this method is called when item is swiped.
//                // below line is to remove item from our array list.
//                recyclerDataArrayList.remove(viewHolder.getAdapterPosition());
//
//                // below line is to notify our item is removed from adapter.
//                recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//
//                // below line is to display our snackbar with action.
//                Snackbar.make(courseRV, deletedCourse.getTitle(), Snackbar.LENGTH_LONG)
//                    .setAction("Undo", new View . OnClickListener () {
//                        @Override
//                        public void onClick(View v) {
//                            // adding on click listener to our action of snack bar.
//                            // below line is to add our item to array list with a position.
//                            recyclerDataArrayList.add(position, deletedCourse);
//
//                            // below line is to notify item is
//                            // added to our adapter class.
//                            recyclerViewAdapter.notifyItemInserted(position);
//                        }
//                    }).show();
//            }
//            // at last we are adding this
//            // to our recycler view.
//        }).attachToRecyclerView(courseRV);
    }

    private fun getNews() {
        val news: Call<News> = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("cheezycode", "error in fetching news")
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                progressBar.visibility = View.GONE
                retryBtn.visibility = View.GONE
                newsList.visibility = View.VISIBLE
                val news: News? = response.body()
                if (news != null) {
                    Log.d("cheezycode", news.articles.toString())
                    articles = news.articles as ArrayList<Articles>
                    adapter = NewsAdapter(mcontext!!, news.articles)
                    newsList.adapter = adapter
                    newsList.layoutManager = LinearLayoutManager(mcontext)


                }
            }
        })
    }

    fun isConnectedToInternet(): Boolean {
        val connectivity: ConnectivityManager =
            mcontext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null) for (netInfo in info) {
                if ((netInfo.typeName.equals(
                        "MOBILE",
                        ignoreCase = true
                    ) || netInfo.typeName.equals("WIFI", ignoreCase = true)) && netInfo.isConnected
                ) {
                    Log.e(
                        "Network : ",
                        "isConnectedToInternet:Connected to Mobile Data " + info.size
                    )
                    return true
                }
            }
        }
        return false
    }

    private fun APICall() {
        if (isConnectedToInternet()) {
            progressBar.visibility = View.VISIBLE
            getNews()

        } else {
            progressBar.visibility = View.GONE
            retryBtn.visibility = View.VISIBLE

        }
    }

//    override fun onItemClick(title: String, desc: String) {
//
//        Toast.makeText(context, "${title} + ${desc}", Toast.LENGTH_SHORT).show()
////        val bundle = Bundle()
//        bundle.putString("title", title)
//        bundle.putString("description", desc)
//
//        val detailFragment = DetailFragment()
//        detailFragment.arguments = bundle
//        activity?.supportFragmentManager!!.beginTransaction()
//            .replace(R.id.fLID, DetailFragment()).addToBackStack(null).commit()
//    }



}