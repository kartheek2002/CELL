package com.example.fragmentproject2;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Fragment2.OnFragmentInteractionListener} interface
 * to handle interaction events. Use the {@link Fragment2#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class Fragment2 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_fragment2, container, false);
	}
}
