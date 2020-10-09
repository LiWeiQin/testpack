package com.weex.app.compment

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import com.taobao.weex.WXSDKInstance
import com.taobao.weex.dom.WXDomObject
import com.taobao.weex.ui.component.WXComponent
import com.taobao.weex.ui.component.WXComponentProp
import com.taobao.weex.ui.component.WXVContainer
import com.weex.app.R


class PhotoViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return try {
            super.onInterceptTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            false
        }
    }
}

class PhotoAdapter : PagerAdapter() {

    private val imageUrls: ArrayList<String> = ArrayList()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val url = imageUrls[position]
        val photoView = PhotoView(container.context)
        Picasso.with(container.context)
                .load(url)
                .into(photoView)
        container.addView(photoView)
        photoView.setOnClickListener {
            Toast.makeText(container.context, "$position", Toast.LENGTH_SHORT).show()
        }
        return photoView
    }

    fun setImageUrls(urls: List<String>) {
        imageUrls.apply {
            clear()
            addAll(urls)
            this@PhotoAdapter.notifyDataSetChanged()
        }
    }

    override fun getCount(): Int {
        return imageUrls.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as? View)
    }

    override fun getItemPosition(obj: Any): Int {
        return POSITION_NONE
    }

    companion object {
        val TAG = PhotoAdapter::class.java.simpleName
    }
}

class ImagePlayerComponent(instance: WXSDKInstance?, dom: WXDomObject?, parent: WXVContainer<*>?) : WXComponent<View>(instance, dom, parent) {


    private var mViewpager: PhotoViewPager? = null
    private var mFrameContainer: FrameLayout? = null
    private var mPhotoAdapter: PhotoAdapter? = null

    override fun initComponentHostView(context: Context): View {
        val component = View.inflate(context, R.layout.layout_image_player, null)
        mFrameContainer = component as? FrameLayout
        initLayout()
        return component
    }

    private fun initLayout() {
        mFrameContainer?.let { container ->
            mViewpager = PhotoViewPager(container.context)
            mViewpager?.let {
                it.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                container.addView(it)
                it.adapter = PhotoAdapter().apply {
                    mPhotoAdapter = this
                }
            }
        }
    }


    @WXComponentProp(name = "imageUrls")
    fun setImageUrls(imageUrls: List<String>) {
        mPhotoAdapter?.run {
            this.setImageUrls(imageUrls)
        }
    }
}
