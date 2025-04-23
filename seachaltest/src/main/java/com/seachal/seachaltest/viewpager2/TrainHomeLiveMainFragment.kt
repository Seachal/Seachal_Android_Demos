package com.seachal.seachaltest.viewpager2

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.seachal.seachaltest.R
import com.seachal.seachaltest.ScrollListFragment.Fragment1
import com.seachal.seachaltest.ScrollListFragment.Fragment2
import com.seachal.seachaltest.ScrollListFragment.Fragment3
import com.seachal.seachaltest.bean.HomeLiveAcademicYearItemBean
import com.xkw.training.adapter.ViewPager2FragmentStateAdapter
import kotlinx.android.synthetic.main.t_fragment_train_home_live_main.o_tab_exercise_categories
import kotlinx.android.synthetic.main.t_fragment_train_home_live_main.t_home_live_main_update_data
import kotlinx.android.synthetic.main.t_fragment_train_home_live_main.t_home_live_main_view_pager
import kotlinx.android.synthetic.main.t_fragment_train_home_live_main.t_home_live_tab_layout


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM_ACADEMIC_LIST = "arg_param_academic_list"


/**
 *
 *  首页直播容器，   包含“我的定于”、 “xx 学年列表”
 * A simple [Fragment] subclass.
 * Use the [TrainHomeLiveMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrainHomeLiveMainFragment : BaseFragment() {


    // 滑动Fragment
    private val titleList = mutableListOf<String>()
    private val fragmentList = mutableListOf<Fragment>()

    //
    //homeLiveAcademicYearItemBean:
    //
    //trainingOrderCouponBean =


    // 学年数据列表
    private var homeLiveAcademicYearItemBeanList: MutableList<HomeLiveAcademicYearItemBean>? = null

//    private var viewPagerAdapter: ViewPager2FragmentStateAdapter? = null

    private  var tabLayoutMediator:TabLayoutMediator? = null

    override fun getContentLayoutId(): Int {
        return R.layout.t_fragment_train_home_live_main
    }

    override fun initData() {
        val list = createHomeLiveAcademicYearItemBeanList()
        updateData(list)
        
        // 初始化底部TabLayout，用于测试tabIndicator
        initExerciseCategoriesTab()
    }

    fun createHomeLiveAcademicYearItemBeanList(): MutableList<HomeLiveAcademicYearItemBean> {
        return mutableListOf(
            HomeLiveAcademicYearItemBean(
                academicYear = "2023-2024",
                currentMonth = "2024-06",
                currentTime = 1717415407831,
                endTime = 1725119999831,
                startTime = 1693497600831
            ),
            HomeLiveAcademicYearItemBean(
                academicYear = "2024-2025",
                currentMonth = null,
                currentTime = null,
                endTime = 1756655999831,
                startTime = 1725120000831
                // currentMonth和currentTime字段不存在，因此不包含在这一项中
            ),
            HomeLiveAcademicYearItemBean(
                academicYear = "2025-2026",
                currentMonth = null,
                currentTime = null,
                endTime = 1788191999831,
                startTime = 1756656000831
                // 同上
            )
//            26-27
            ,
            HomeLiveAcademicYearItemBean(
                academicYear = "2026-2027",
                currentMonth = null,
                currentTime = null,
                endTime = 1820323999831,
                startTime = 1788192000831
                // 同上
            )


        )
    }

    override fun initListeners() {
        t_home_live_main_update_data.setOnClickListener {
            val list = createHomeLiveAcademicYearItemBeanList()
            updateData(list)
        }
        
        // 添加底部TabLayout的选择监听
        o_tab_exercise_categories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 处理选中状态
                tab?.let {
                    // 可以在这里处理选中逻辑
                }
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 处理未选中状态
            }
            
            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 处理重新选中状态
            }
        })
    }

    override fun loadData() {

    }
    
    /**
     * 初始化底部练习分类TabLayout
     * 用于测试tabIndicator效果
     */
    private fun initExerciseCategoriesTab() {
        // 清除已有Tab
        o_tab_exercise_categories.removeAllTabs()
        // 添加测试数据
        val categories = listOf("全部", "语文", "思想政治","数学", "英语", "物理", "化学", "生物", "历史", "地理")
        
        // 创建并添加Tab
        categories.forEach { category ->
            val tab = o_tab_exercise_categories.newTab().apply {
                text = category
            }
            o_tab_exercise_categories.addTab(tab)
        }
        
        // 默认选中第一个
        o_tab_exercise_categories.getTabAt(0)?.select()
    }


    private fun updateFragmentListNew() {


        //初始化FragmentList
        if (titleList.isNotEmpty()) {
            titleList.clear()
        }
        if (fragmentList.isNotEmpty()) {
            fragmentList.clear()
        }
//         清空数据的时候刷新一下
        t_home_live_main_view_pager.adapter?.notifyDataSetChanged()



        homeLiveAcademicYearItemBeanList?.let { list ->
            val listSort = list.reversed()
            // 当前学年的索引
            var indexCurrentAcademic = listSort.indexOfFirst { it.isCurrentAcademic }
            for (i in 0 until listSort.size){
                listSort[i]?.formattedAcademicYear?.let {
                    titleList.add(it)
                    when(i){
                        0 -> {
                            fragmentList.add(Fragment1())
                        }
                        1 -> {
                            fragmentList.add(Fragment2())
                        }
                        2 -> {
                            fragmentList.add(Fragment3())
                        }

                        else -> {}
                    }
                }
            }

            // 如果列表包含 我的预约， 索引+1.
            if (titleList.contains(TYPE_TAB_NAME)) {
                indexCurrentAcademic = indexCurrentAcademic + 1
            }
            if (fragmentList.isNotEmpty()) {
                initTabAndViewPager(titleList.size, indexCurrentAcademic)
            }
        } ?: run {
            //liveViewModel.loadAcademicYearList(defaultStageId, defaultSubjectId)
        }
    }


    private fun initTabAndViewPager(offscreenPageLimit: Int, currentIndex: Int) {
        // 设置ViewPager2的offscreenPageLimit, 设置为
        t_home_live_main_view_pager.offscreenPageLimit = offscreenPageLimit

        if (t_home_live_main_view_pager.adapter == null) {
            t_home_live_main_view_pager.adapter = ViewPager2FragmentStateAdapter(this, fragmentList)
        }else{
            t_home_live_main_view_pager.adapter?.notifyDataSetChanged()
        }

//        tabLayoutMediator?.let {
//            it.detach()
//
//        } ?: run {
//            tabLayoutMediator = TabLayoutMediator(
//                t_home_live_tab_layout,
//                t_home_live_main_view_pager
//            ) { tab, position ->
////                tab.text = titleList.get(position)
//            }
//        }
//        tabLayoutMediator?.attach()
//        var tabLayoutMediator = TabLayoutMediator(
//            t_home_live_tab_layout,
//            t_home_live_main_view_pager
//        ) { tab, position ->
//            tab.text = titleList.get(position)
//        }
//        tabLayoutMediator.attach()
//         下面这段代码的顺序很重要，
        t_home_live_tab_layout.clearOnTabSelectedListeners()
        t_home_live_tab_layout.removeAllTabs()
        Log.e("currentIndexLog:", currentIndex.toString())
        setTabSelectedListener()
        setTabCustomView()
//        t_home_live_main_view_pager.setCurrentItem(currentIndex, false)
        t_home_live_tab_layout.getTabAt(currentIndex)?.select()

        hideLoading()
    }

    private fun setTabCustomView() {
        for (index in titleList.indices) {
            val tab = t_home_live_tab_layout.newTab()
            tab.let {
//                 设置自定义 ViewTab
                val inflater = LayoutInflater.from(context)
                val tabContainerView = (inflater.inflate(R.layout.t_item_tab_layout_background_corner, it.view, false))
                val taCustomTextView = tabContainerView.findViewById<TextView>(R.id.tv_tab_layout_title)
                it.setCustomView(taCustomTextView)
                it.customView?.findViewById<TextView>(R.id.tv_tab_layout_title)?.run {
                    if (this is TextView) {
                        text = titleList[index]
                        // 当前 tab 设置选中效果
                        setTabSelectStyle(t_home_live_main_view_pager.currentItem == index)
                    }
                }
            }
            t_home_live_tab_layout.addTab(tab)
        }
    }


    private fun TextView.setTabSelectStyle(select: Boolean) {
        if (select) {
            context?.let { con ->
                textSize = 16f
                typeface = Typeface.DEFAULT_BOLD
                setTextColor(ContextCompat.getColor(con, R.color.white))
                setBackgroundResource(R.drawable.t_shape_bg_solid_ff6b00_37px)

            }
        } else {
            context?.let { con ->
                textSize = 13f
                typeface = Typeface.DEFAULT
                setTextColor(ContextCompat.getColor(con, R.color.common66))
                setBackgroundResource(R.drawable.t_shape_bg_solid_f4f4f4_37px)
            }
        }
    }


    private fun setTabSelectedListener() {

        t_home_live_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.run {
                    if (this is TextView) {
                        setTabSelectStyle(false)
                    }
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.run {
                    if (this is TextView) {
                        setTabSelectStyle(true)
                        t_home_live_main_view_pager.setCurrentItem(tab.position, false)
                    }
                }
            }
        })
    }


    fun updateData(newData: MutableList<HomeLiveAcademicYearItemBean>?) {
        homeLiveAcademicYearItemBeanList = newData
        updatePage()
    }

    fun updatePage() {
        // 更新界面
        updateFragmentListNew()
        //setTabSelectedListener()


    }


    companion object {


        const val TYPE_TAB_NAME = "我的预约"


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TrainHomeLiveMainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(homeLiveAcademicYearItemBean: ArrayList<HomeLiveAcademicYearItemBean>?) =
            TrainHomeLiveMainFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM_ACADEMIC_LIST, homeLiveAcademicYearItemBean)
                }
            }
    }
}