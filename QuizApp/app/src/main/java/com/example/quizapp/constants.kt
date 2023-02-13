package com.example.quizapp

object constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTION: String = "total_question"
    const val CORRECT_ANSWER: String = "correct_answers"

    fun getQuestions(): ArrayList<Questions>{
        val questionList = ArrayList<Questions>()
        val que1 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_argentina,
            "Argentenia",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questionList.add(que1)
        val que2 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_australia,
            "Argentenia",
            "Australia",
            "Armenia",
            "Austria",
            2
        )
        questionList.add(que2)
        val que3 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_belgium,
            "Argentenia",
            "Australia",
            "belgium",
            "Austria",
            3
        )
        questionList.add(que3)
        val que4 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_brazil,
            "Argentenia",
            "Australia",
            "Armenia",
            "brazil",
            4
        )
        questionList.add(que4)
        val que5 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_denmark,
            "denmark",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questionList.add(que5)
        val que6 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_fiji,
            "Argentenia",
            "Australia",
            "fiji",
            "Austria",
            3
        )
        questionList.add(que6)
        val que7 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_germany,
            "Argentenia",
            "Germany",
            "Armenia",
            "Austria",
            2
        )
        questionList.add(que7)
        val que8 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_india,
            "India",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questionList.add(que8)
        val que9 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_kuwait,
            "Argentenia",
            "Australia",
            "Armenia",
            "Kuwait",
            4
        )
        questionList.add(que9)
        val que10 = Questions(1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_new_zealand,
            "Argentenia",
            "New Zealand",
            "Armenia",
            "Austria",
            2
        )
        questionList.add(que10)

        return questionList
    }
}