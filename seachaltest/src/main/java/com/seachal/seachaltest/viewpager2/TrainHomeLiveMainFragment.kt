package com.seachal.seachaltest.viewpager2

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.seachal.seachaltest.R
import com.seachal.seachaltest.ScrollListFragment.Fragment1
import com.seachal.seachaltest.bean.HomeLiveAcademicYearItemBean
import com.xkw.training.adapter.ViewPager2FragmentStateAdapter
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
    override fun getContentLayoutId(): Int {
        return R.layout.t_fragment_train_home_live_main
    }

    override fun initData() {
        val list = createHomeLiveAcademicYearItemBeanList()
        updateData(list)
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
        )
    }
    override fun initListeners() {
        t_home_live_main_update_data.setOnClickListener {
            val list = createHomeLiveAcademicYearItemBeanList()
            updateData(list)
        }
    }

    override fun loadData() {

    }


    private fun updateFragmentListNew() {


        //初始化FragmentList
        if (titleList.isNotEmpty()) {
            titleList.clear()
        }
        if (fragmentList.isNotEmpty()) {
            fragmentList.clear()
        }
        t_home_live_tab_layout.removeAllTabs()




        homeLiveAcademicYearItemBeanList?.let { list ->
            val listSort = list.reversed()
            // 当前学年的索引
            var indexCurrentAcademic = listSort.indexOfFirst { it.isCurrentAcademic }
            for (academicBean in listSort) {
                academicBean.formattedAcademicYear?.let {
                    titleList.add(it)
                    fragmentList.add(Fragment1())
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
        // 为了解决更新 Fragmentlist 后  tab 出现可以多个被选中的情况

        // 设置ViewPager2的offscreenPageLimit, 设置为
        t_home_live_main_view_pager.offscreenPageLimit = offscreenPageLimit
        t_home_live_main_view_pager.adapter =
            ViewPager2FragmentStateAdapter(this, fragmentList)

        t_home_live_tab_layout.clearOnTabSelectedListeners()

        var tabLayoutMediator = TabLayoutMediator(t_home_live_tab_layout, t_home_live_main_view_pager) { tab, position ->
            tab.text = titleList.get(position)
        }
        tabLayoutMediator.attach()

        Log.e("currentIndexLog:", currentIndex.toString())
        t_home_live_main_view_pager.setCurrentItem(currentIndex, false)
        setTabCustomView()
        setTabSelectedListener()
        hideLoading()
    }

    private fun setTabCustomView() {
        for (index in titleList.indices) {
            val tab = t_home_live_tab_layout.getTabAt(index)
            tab?.let {
                it.setCustomView(R.layout.t_item_tab_layout_background_corner)
                it.customView.run {
                    if (this is TextView) {
                        text = titleList[index]
                        // 当前 tab 设置选中效果
                        setTabSelectStyle(t_home_live_main_view_pager.currentItem == index)
                    }
                }
            }
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