package com.example.fragmentproject3;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Fragment2.OnFragmentInteractionListener} interface
 * to handle interaction events. Use the {@link Fragment2#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class Fragment2 extends Fragment implements OnClickListener{
	Button btn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
		
		btn = (Button) view.findViewById(R.id.gettext);
        btn.setOnClickListener(this);
        
        return view;
	}
	
	@Override
    public void onClick(View v) {
		FragmentManager fm = getFragmentManager();
		Fragment one = fm.findFragmentById(R.id.fragment1);
		String text = one.getTag();
        Toast.makeText(this.getActivity(), text, Toast.LENGTH_LONG).show();
    }
}
