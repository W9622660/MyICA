package uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.appEntry

import uk.ac.tees.w9622660.ukpolicenewsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}