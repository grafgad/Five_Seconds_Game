package com.example.fivesecondsgame.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fivesecondsgame.R
import com.example.fivesecondsgame.databinding.FragmentSettingsBinding
import com.example.fivesecondsgame.presentation.main.MainFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        binding.startGame.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun bottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun getViewBinding(container: ViewGroup?) =
        FragmentSettingsBinding.inflate(layoutInflater, container, false)

    companion object {
        fun newInstance() = SettingsFragment()
    }
}