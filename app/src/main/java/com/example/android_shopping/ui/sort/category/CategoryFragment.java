package com.example.android_shopping.ui.sort.category;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.android_shopping.R;
import com.example.android_shopping.base.BaseAdapter;
import com.example.android_shopping.base.BaseFragment;
import com.example.android_shopping.interfaces.category.ICategory;
import com.example.android_shopping.module.data.CatalogListBase;
import com.example.android_shopping.presenter.category.CategotyPersenter;
import com.example.android_shopping.ui.channel.ChannelActivity;
import com.example.android_shopping.ui.sort.category.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment<CategotyPersenter> implements ICategory.View {

    @BindView(R.id.image_image)
    ImageView imageImage;
    @BindView(R.id.tv_fl)
    TextView tvFl;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.tv_front_name)
    TextView tvFrontName;
    private int id;
    private ArrayList<CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryListBeans;
    private CategoryAdapter categoryAdapter;


    @Override
    public int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView() {
        id = getArguments().getInt( "id" );
        subCategoryListBeans = new ArrayList<>();

        rvCategory.setLayoutManager( new StaggeredGridLayoutManager( 3, StaggeredGridLayoutManager.VERTICAL ) );
        categoryAdapter = new CategoryAdapter( getActivity(), subCategoryListBeans );
        rvCategory.setAdapter( categoryAdapter );

        categoryAdapter.addListClick( new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean subCategoryListBean = subCategoryListBeans.get( pos );
                Intent intent = new Intent( getActivity(), ChannelActivity.class );
                intent.putExtra( "caId",subCategoryListBean.getId());
                intent.putExtra( "tabName",subCategoryListBean.getName());
                startActivity( intent );
            }
        } );

    }

    @Override
    public CategotyPersenter createPersenter() {
        return new CategotyPersenter( this );
    }

    @Override
    public void initData() {
        persenter.getCategory( id );
    }

    @Override
    public void getCategoryReturn(CatalogListBase catalogListBase) {

        CatalogListBase.DataBean.CurrentCategoryBean currentCategory = catalogListBase.getData().getCurrentCategory();

        Glide.with( getActivity() ).load( currentCategory.getWap_banner_url() ).into( imageImage );
        tvFl.setText( "————" + currentCategory.getName() + "分类————" );
        tvFrontName.setText( currentCategory.getFront_name() );
        subCategoryListBeans.clear();
        List<CatalogListBase.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = catalogListBase.getData().getCurrentCategory().getSubCategoryList();
        subCategoryListBeans.addAll( subCategoryList );
        categoryAdapter.notifyDataSetChanged();


    }
}
