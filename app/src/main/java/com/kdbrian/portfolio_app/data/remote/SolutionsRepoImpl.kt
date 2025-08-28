package com.kdbrian.portfolio_app.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kdbrian.portfolio_app.domain.model.Solution
import com.kdbrian.portfolio_app.domain.repo.SolutionsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SolutionsRepoImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : SolutionsRepo {

    override suspend fun loadSolutions(): Result<List<Solution>> = withContext(Dispatchers.IO) {
        try {
            val snapshot = firestore.collection(collectionName).get().await()
            if (snapshot.isEmpty)
                Result.success(emptyList())
            else {
                val solutions = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Solution::class.java)
                }
                Result.success(solutions)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loadSolution(id: String): Result<Solution> = withContext(Dispatchers.IO) {
        try {
            val snapshot = firestore.collection(collectionName).document(id).get().await()
            if (!snapshot.exists())
                Result.failure(Exception("Solution not found"))
            else {
                val solution = snapshot.toObject(Solution::class.java)
                Result.success(solution!!)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addSolution(solution: Solution): Result<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                val documentReference = firestore
                    .collection(collectionName)
                    .document()
                documentReference
                    .set(solution.copy(id = documentReference.id, owner = auth.currentUser!!.uid))
                    .await()
                Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun deleteSolution(id: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            firestore.collection(collectionName).document(id).delete().await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}