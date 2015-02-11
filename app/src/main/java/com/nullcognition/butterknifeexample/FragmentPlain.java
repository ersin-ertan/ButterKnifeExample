package com.nullcognition.butterknifeexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FragmentPlain extends Fragment {

   private static final String id_number = "fragmentPlainIdNumber";

   @InjectView(R.id.textView02)
   TextView textView;

   public FragmentPlain(){}

   /* Due to injection and the lifecycle of the fragment being different than the activity, meaning it could be destroyed and created multiple times,
   ButterKnife provides the rest method to set all of the views to null
   * */

   @Override
   public void onDestroyView(){
	  super.onDestroyView();

	  ButterKnife.reset(this);
   }

   public static FragmentPlain newInstance(int inId){
	  FragmentPlain fragment = new FragmentPlain();

	  Bundle args = new Bundle();
	  args.putInt(id_number, inId);
	  fragment.setArguments(args);

	  return fragment;
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	  View rootView = inflater.inflate(R.layout.fragment_frag_layout, container, false);

	  ButterKnife.inject(this, rootView);

	  return rootView;
   }
}
