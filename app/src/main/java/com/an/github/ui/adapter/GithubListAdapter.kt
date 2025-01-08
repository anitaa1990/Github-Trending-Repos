package com.an.github.ui.adapter

import android.content.Context
import com.an.github.ui.custom.recyclerview.RecyclerLayoutClickListener

class GithubListAdapter(private val context: Context, private val listener: RecyclerLayoutClickListener)
//    : RecyclerView.Adapter<GithubListAdapter.CustomViewHolder>(), Filterable
    {
//    private var items: MutableList<GithubEntity> = mutableListOf()
//    private val filteredItems: MutableList<GithubEntity> = mutableListOf()
//
//    private var lastFilteredLanguage = "All"
//
//    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CustomViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//
//        val itemBinding = RepoListItemBinding.inflate(layoutInflater, parent, false)
//        val customItemViewHolder = CustomViewHolder(itemBinding)
//
////        itemBinding.cardView.setOnClickListener { v -> customItemViewHolder.onLayoutButtonClick() }
////        itemBinding.btnShare.setOnClickListener { view -> customItemViewHolder.onShareButtonClick() }
//
//        return customItemViewHolder
//    }
//
//    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        holder.bindTo(getItem(position))
//    }
//
//    fun setItems(items: List<GithubEntity>?) {
//        items?.withNotNullNorEmpty {
//            filteredItems.addAll(items)
//            filter.filter(lastFilteredLanguage)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//
//    fun getItem(position: Int): GithubEntity {
//        return items[position]
//    }
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
//                val language = charSequence.toString()
//                lastFilteredLanguage = language
//
//                if (language.equals("All", ignoreCase = true)) {
//                    items = filteredItems
//
//                } else {
//                    val list = ArrayList<GithubEntity>()
//                    for (githubEntity in filteredItems) {
//
//                        if (language.equals(githubEntity.language, ignoreCase = true)) {
//                            list.add(githubEntity)
//                        }
//                    }
//                    items = list
//                }
//
//                val filterResults = Filter.FilterResults()
//                filterResults.values = items
//                return filterResults
//            }
//
//            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
//                items = filterResults.values as MutableList<GithubEntity>
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    inner class CustomViewHolder(private val binding: RepoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bindTo(githubEntity: GithubEntity) {
//            Picasso.get().load(githubEntity.owner.avatarUrl)
//                    .placeholder(R.drawable.ic_placeholder)
//                    .into(binding.itemProfileImg)
//
//            binding.itemTitle.text = githubEntity.fullName
//            binding.itemTime.text = String.format(context.getString(R.string.item_date),
//                    AppUtils.getDate(githubEntity.createdAt),
//                    AppUtils.getTime(githubEntity.createdAt))
//
//            binding.itemDesc.text = githubEntity.description
//
//
//            githubEntity.language?.let {
//                binding.itemImgLanguage.visibility = View.VISIBLE
//                binding.itemLikes.visibility = View.VISIBLE
//                binding.itemLikes.text = githubEntity.language
//
//                val drawable = context.resources.getDrawable(R.drawable.ic_circle) as GradientDrawable
//                drawable.setColor(AppUtils.getColorByLanguage(context, githubEntity.language))
//                binding.itemImgLanguage.setImageDrawable(drawable)
//            } ?: run {
//                binding.itemImgLanguage.visibility = View.GONE
//                binding.itemLikes.visibility = View.GONE
//            }
//        }
//
//        internal fun onLayoutButtonClick() {
//            listener.redirectToDetailScreen(binding.itemProfileImg, binding.itemTitle, binding.itemImgLanguage, binding.itemLikes, getItem(layoutPosition))
//        }
//
//        internal fun onShareButtonClick() {
//            listener.sharePost(getItem(layoutPosition))
//        }
//    }
}
