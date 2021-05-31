package com.example.l7_20demo1;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.l7_20demo1.databinding.FragmentLossBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LossFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LossFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LossFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LossFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LossFragment newInstance(String param1, String param2) {
        LossFragment fragment = new LossFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LiveData liveData;
//        liveData = new ViewModelProvider(this,
//                new SavedStateViewModelFactory(getActivity().getApplication(),this)).get(LiveData.class);
        liveData = new ViewModelProvider(this,
                new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(LiveData.class);
        FragmentLossBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loss, container, false);
        binding.setData(liveData);
        binding.setLifecycleOwner(requireActivity());
        binding.button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_lossFragment_to_titleFragment);
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_loss, container, false);
        return binding.getRoot();
    }
}