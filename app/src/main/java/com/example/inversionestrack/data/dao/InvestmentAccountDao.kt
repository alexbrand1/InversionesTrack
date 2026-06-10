package com.example.inversionestrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inversionestrack.data.model.InvestmentAccount
import com.example.inversionestrack.data.model.InvestmentLayer
import kotlinx.coroutines.flow.Flow

@Dao
interface InvestmentAccountDao {

    // Agregar nueva cuenta a una capa
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: InvestmentAccount): Long

    // Modificar cuenta existente
    @Update
    suspend fun update(account: InvestmentAccount)


    // Eliminar cuenta
    @Delete
    suspend fun delete(account: InvestmentAccount)


    // Todas las cuentas del usuario para dashboard global
    @Query("SELECT * FROM investment_accounts WHERE userId = :userId")
    fun getAllByUserId(userId: Long): Flow<List<InvestmentAccount>>

    // Cuentas por capa para cada pantalla de nivel
    @Query("SELECT * FROM investment_accounts WHERE userId = :userId AND layer = :layer")
    fun getByLayer(userId: Long, layer: InvestmentLayer): Flow<List<InvestmentAccount>>


    // Suma total del patrimonio para calcular porcentajes de rebalanceo
    @Query("SELECT SUM(amount) FROM investment_accounts WHERE userId = :userId")
    fun getTotalPatrimony(userId: Long): Flow<Double?>

    // Suma por capa para calcular % de cumplimiento de cada nivel
    @Query("SELECT SUM(amount) FROM investment_accounts WHERE userId = :userId AND layer = :layer")
    fun getTotalByLayer(userId: Long, layer: InvestmentLayer): Flow<Double?>


    // Buscar por id para editar una cuenta específica
    @Query("SELECT * FROM investment_accounts WHERE id = :accountId")
    suspend fun getById(accountId: Long): InvestmentAccount?
}