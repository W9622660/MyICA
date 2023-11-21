package uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.appEntry

import uk.ac.tees.w9622660.ukpolicenewsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUserManager: LocalUserManager
){
    operator fun invoke() : Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}