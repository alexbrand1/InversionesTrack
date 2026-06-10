package com.example.inversionestrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inversionestrack.data.model.SavingsProject
import kotlinx.coroutines.flow.Flow

@Dao
interface SavingsProjectDao {

    // Crea nuevo proyecto de ahorro
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(project: SavingsProject): Long

    // Actualiza cuando el usuario registra un abono
    @Update
    suspend fun update(project: SavingsProject)

    // Eliminar proyecto
    @Delete
    suspend fun delete(project: SavingsProject)

    // Obtiene todos los proyectos del usuario
    @Query("SELECT * FROM savings_projects WHERE userId = :userId")
    fun getAllByUserId(userId: Long): Flow<List<SavingsProject>>

    // Proyectos en riesgo  donde el ritmo de ahorro no alcanza la fecha de vencimiento
    @Query("""
        SELECT * FROM savings_projects 
        WHERE userId = :userId 
        AND deadlineDate < :currentDate 
        AND currentAmount < targetAmount
    """)
    fun getAtRiskProjects(userId: Long, currentDate: Long): Flow<List<SavingsProject>>

    // Busca por id para editar un proyecto específico
    @Query("SELECT * FROM savings_projects WHERE id = :projectId")
    suspend fun getById(projectId: Long): SavingsProject?
}